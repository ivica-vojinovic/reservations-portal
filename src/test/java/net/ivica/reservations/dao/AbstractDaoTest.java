//package net.ivica.reservations.dao;
//
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.Rule;
//import org.junit.rules.TestName;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.jdbc.datasource.SingleConnectionDataSource;
//import org.springframework.jdbc.datasource.init.ScriptUtils;
//import org.springframework.mock.jndi.SimpleNamingContextBuilder;
//import org.springframework.test.annotation.Commit;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
//import org.springframework.test.context.transaction.BeforeTransaction;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.transaction.annotation.Transactional;
//
//import net.emisia.football.account.manager.dao.ClubProfileDao;
//import net.emisia.football.account.manager.dao.TeamCoachDao;
//import net.emisia.football.account.manager.dao.TeamDao;
//import net.emisia.football.account.manager.model.StringConstants;
//import net.emisia.football.account.manager.model.test.annotations.TestCase;
//import net.emisia.football.account.manager.model.test.annotations.TestSuite;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//	"classpath:net/emisia/football/account/manager/dao/spring/applicationContext-hibernate.xml" })
//@Commit
//@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
//	DirtiesContextTestExecutionListener.class })
//@Transactional
//public abstract class AbstractDaoTest {
//    private static final String CLEAN_DATA_SET = "data-sets/cleanDataSet.xml";
//    private static final String CREATE_SCRIPT = "scripts/create.sql";
//    private static final String DATA_SETS_ROOT_FOLDER = "data-sets";
//    private static final String TEST_CASE_EXTENSION = ".xml";
//
//    static {
//	try {
//	    SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
//
//	    SingleConnectionDataSource ds = new SingleConnectionDataSource();
//
//	    // ds.setDriverClassName("org.h2.Driver");
//	    // ds.setUrl("jdbc:h2:mem:test;create=true");
//	    ds.setDriverClassName("net.sf.log4jdbc.DriverSpy");
//	    ds.setUrl("jdbc:log4jdbc:mariadb://172.16.11.222:3306/facme?useUnicode=true&amp;characterEncoding=UTF8");
//	    ds.setSuppressClose(true);
//	    ds.setUsername("fwire");
//	    ds.setPassword("fwire");
//
//	    createInitialDataStructure(ds);
//
//	    builder.bind("java:comp/env/jdbc/facme", ds);
//	} catch (Exception e) {
//	    throw new RuntimeException(e);
//	}
//
//    }
//
//    static void createInitialDataStructure(DataSource dataSource) throws SQLException {
//	Resource createScript = new ClassPathResource(CREATE_SCRIPT);
//
//	try (Connection connection = dataSource.getConnection()) {
//	    connection.createStatement().execute("SET foreign_key_checks = 0;");
//	    ScriptUtils.executeSqlScript(connection, createScript);
//	    connection.createStatement().execute("SET foreign_key_checks = 1;");
//	}
//    }
//
//    @Autowired
//    private ClubProfileDao _clubProfileDao;
//    @Autowired
//    private DataSource _dataSource;
//    private DbUnitHelper _dbUnitHelper;
//    @Autowired
//    private SessionFactory _sessionFactory;
//    @Autowired
//    private TeamCoachDao _teamCoachDao;
//    @Autowired
//    private TeamDao _teamDao;
//
//    @Rule
//    public TestName _methodName = new TestName();
//
//    @BeforeTransaction
//    public void setUp() throws Exception {
//	String baseDataSet = getBaseDataSet();
//
//	_dbUnitHelper = new DbUnitHelper(getDataSource());
//
//	FlatXmlDataSetBuilder builderClean = new FlatXmlDataSetBuilder();
//	builderClean.setColumnSensing(true);
//	IDataSet cleanDataSet = builderClean.build(getClass().getClassLoader().getResourceAsStream(CLEAN_DATA_SET));
//	_dbUnitHelper.cleanDatabase(cleanDataSet);
//
//	if (StringUtils.isNotEmpty(baseDataSet)) {
//	    FlatXmlDataSetBuilder builderData = new FlatXmlDataSetBuilder();
//	    builderData.setColumnSensing(true);
//	    IDataSet dataSet = builderData.build(getClass().getClassLoader().getResourceAsStream(baseDataSet));
//	    _dbUnitHelper.setupDatabase(dataSet);
//	}
//    }
//
//    private String getBaseDataSet() {
//	String result = null;
//	try {
//	    TestCase testCase = getTestCaseSetup();
//
//	    TestSuite testSuite = this.getClass().getAnnotation(TestSuite.class);
//
//	    if (testCase != null) {
//		StringBuilder baseDataSetPathBuilder = new StringBuilder(DATA_SETS_ROOT_FOLDER);
//		baseDataSetPathBuilder.append(StringConstants.SLASH);
//		baseDataSetPathBuilder.append(testSuite.value());
//		baseDataSetPathBuilder.append(StringConstants.SLASH);
//		baseDataSetPathBuilder.append(testCase.value());
//		baseDataSetPathBuilder.append(TEST_CASE_EXTENSION);
//
//		result = baseDataSetPathBuilder.toString();
//	    }
//	} catch (Exception e) {
//	    throw new RuntimeException(e);
//	}
//
//	return result;
//    }
//
//    private DataSource getDataSource() {
//	return _dataSource;
//    }
//
//    private SessionFactory getSessionFactory() {
//	return _sessionFactory;
//    }
//
//    private TestCase getTestCaseSetup() throws NoSuchMethodException {
//	Method testMethod = this.getClass().getMethod(_methodName.getMethodName());
//	return testMethod.getAnnotation(TestCase.class);
//    }
//
//    protected void flushSession() {
//	Session session = getCurrentSession();
//
//	session.flush();
//	session.clear();
//    }
//
//    protected final ClubProfileDao getClubProfileDao() {
//	return _clubProfileDao;
//    }
//
//    protected Session getCurrentSession() {
//	return getSessionFactory().getCurrentSession();
//    }
//
//    protected final TeamCoachDao getTeamCoachDao() {
//	return _teamCoachDao;
//    }
//
//    protected final TeamDao getTeamDao() {
//	return _teamDao;
//    }
//}
