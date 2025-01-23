package com.joshi.manufacturer.jset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;

import java.util.ArrayList;
import java.util.List;

import org.h2.jdbc.JdbcException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
//@Sql({"/h2-schema.sql", "/h2-data.sql"})
@Sql({"/mysql-schema.sql", "/mysql-data.sql"})
@TestMethodOrder(OrderAnnotation.class)
class H2DBConstraintsTest {
	
	private static List<H2Statement> sqls;
	
    @Autowired
    private JdbcTemplate jt;
    
//    @BeforeAll
    static void init() throws Exception {
    	log.debug("Loading sql statements...");
    	List<String> lines = linesOf(H2DBConstraintsTest.class.getResource("/h2-error-prone-sqls.txt"));
    	final int linePerRec = 3;
    	assertThat(lines).isNotNull();
    	String line1 = null;
    	sqls = new ArrayList<H2Statement>();
    	for (int i = 0; i < lines.size(); i++) {
    		String line = lines.get(i);
    		if (i % linePerRec == 0) {
    			// Comment
    			if (!line.startsWith("--")) {
    				throw new Exception(String.format("Line %d:: SQL Comment must start with --", i));
    			}
    			line1 = line;
    		} else if (i % linePerRec == 1) {
    			//Statement
    			if (line.startsWith("--")) {
    				throw new Exception(String.format("Line %d:: SQL Statement cannot start with --", i));
    			}
        		H2Statement stmt = new H2Statement();
        		stmt.comment = line1;
    			stmt.stmt = line;
    			sqls.add(stmt);
    			line1 = null;
    		} else {
    			//Ignore empty line
    		}
		}
    	log.debug("Loaded {} sql statements.", sqls.size());
    }
	
	@Test
	@Order(1)
	void test010Template() throws Exception {
		log.debug("Checking DB Connectivity...");
		assertThat(jt).isNotNull();
		assertThat(jt.getDataSource());
		H2DBConstraintsTest.init();
		assertThat(sqls).hasAtLeastOneElementOfType(H2Statement.class);
	}
	
	@Test
	@Order(2)
	void test090RunStmt() {
		log.debug("testRunStmt...");
		assertThat(sqls).isNotNull();
		for (int i = 0; i < sqls.size(); i++) {
			H2Statement stmt = sqls.get(i);
			log.debug("testRunStmt::{}::{}", i, stmt.stmt);
			assertThat(stmt).isNotNull();
			try {
				jt.execute(stmt.stmt);
			} catch (DataAccessException e) {
				e.printStackTrace();
				if (e.getMostSpecificCause() instanceof JdbcException h2e) {
					stmt.errorCode = h2e.getErrorCode();
					stmt.originalMessage = h2e.getOriginalMessage();
					log.debug("SQL Error:{}|{}", stmt.errorCode, stmt.originalMessage);
				}
			}
		}
	}
	
	@Test
	@Order(3)
	void test999PrintErrors() {
		log.debug("testPrintErrors...");
		assertThat(sqls).isNotNull();
		sqls.forEach(System.out::println);
	}
	

}

class H2Statement {
	String comment;
	String stmt;
	int errorCode;
	String originalMessage;
	
	public String toString() {
		return errorCode + "|" + originalMessage;
	}
}