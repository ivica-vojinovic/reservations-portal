package net.ivica.reservations.dao.utils;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.*;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbUnitHelper {

    private DataSource _dataSource;

    public DbUnitHelper(DataSource dbSource) {
        _dataSource = dbSource;
    }

    public void assertDatabaseTables(String tableName, File expectedDataSetFile) {
        IDatabaseConnection dbConnection = getDatabaseConnection();

        try {
            IDataSet databaseDataSet = dbConnection.createDataSet();
            ITable actualTable = databaseDataSet.getTable(tableName);

            IDataSet expectedDataSet = createDataSetFromFile(expectedDataSetFile);

            ITable expectedTable = expectedDataSet.getTable(tableName);

            ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                    expectedTable.getTableMetaData().getColumns());

            Assertion.assertEquals(expectedTable, filteredTable);
        } catch (DataSetException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseUnitException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cleanDatabase(IDataSet cleanDataSet) throws Exception {
        IDatabaseConnection connection = getDatabaseConnection();

        DatabaseOperation.DELETE_ALL.execute(connection, cleanDataSet);

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private IDataSet createDataSetFromFile(File dataSetFile) {
        IDataSet expectedDataSet;

        try (InputStream expectedDataSetStream = new FileInputStream(dataSetFile)) {
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            expectedDataSet = builder.build(expectedDataSetStream);
        } catch (DataSetException | IOException e) {
            throw new RuntimeException(e);
        }

        return expectedDataSet;
    }

    public IDataSet createSnapshot() {
        IDatabaseConnection conn = getDatabaseConnection();

        IDataSet dataset;
        try {
            dataset = conn.createDataSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return dataset;
    }

    public Object[][] executeQuery(String query) {
        IDatabaseConnection dbConn = getDatabaseConnection();
        try {
            QueryDataSet dataSet = new QueryDataSet(dbConn);
            dataSet.addTable("RESULT", query); //$NON-NLS-1$

            ITableIterator iterator = dataSet.iterator();
            iterator.next();
            ITable table = iterator.getTable();

            ITableMetaData tableMetaData = table.getTableMetaData();
            Column[] columns = tableMetaData.getColumns();

            Object[][] result = new Object[table.getRowCount() + 1][columns.length];
            for (int i = 0; i < columns.length; i++) {
                result[0][i] = columns[i].getColumnName();
            }

            for (int i = 1; i <= table.getRowCount(); i++) {
                for (int j = 0; j < columns.length; j++) {
                    Column column = columns[j];

                    result[i][j] = table.getValue(i - 1, column.getColumnName());
                }
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dbConn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void executeQuery(String query, OutputStream outputStream) {
        IDatabaseConnection dbConn = getDatabaseConnection();
        try {
            QueryDataSet dataSet = new QueryDataSet(dbConn);
            dataSet.addTable("RESULT", query); //$NON-NLS-1$

            FlatXmlDataSet.write(dataSet, outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dbConn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void executeUpdate(String query) {
        IDatabaseConnection dbConn = getDatabaseConnection();
        try (Statement statement = dbConn.getConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dbConn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void exportDatabase(OutputStream outputStream, List<String> tablePrefix) {
        try {
            IDatabaseConnection conn = getDatabaseConnection();
            IDataSet fullDataSet = conn.createDataSet();

            String[] databaseTableNames = fullDataSet.getTableNames();
            List<String> exportTableNames = new ArrayList<>();
            if (tablePrefix == null) {
                exportTableNames.addAll(Arrays.asList(databaseTableNames));
            } else {
                for (String tableName : databaseTableNames) {
                    boolean tableForExport = true;
                    for (String prefix : tablePrefix) {
                        if (tableName.startsWith(prefix)) {
                            tableForExport = false;
                            break;
                        }
                    }
                    if (tableForExport) {
                        exportTableNames.add(tableName);
                    }
                }
            }
            IDataSet partialDataset = conn.createDataSet(exportTableNames.toArray(new String[exportTableNames.size()]));

            FlatXmlDataSet.write(partialDataset, outputStream);
        } catch (DataSetException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportDatabase(String outputFileName) {
        exportDatabase(null, outputFileName);
    }

    public void exportDatabase(String[] tableNames, String outputFileName) {
        int _outputBufferSize = 1024 * 1024;

        try {
            IDatabaseConnection conn = getDatabaseConnection();
            IDataSet dataSet;
            if (tableNames == null) {
                dataSet = conn.createDataSet();
                dataSet = conn.createDataSet(new String[]{"INFORMATION_SCHEMA.TYPE_INFO"});
            } else {
                dataSet = conn.createDataSet(tableNames);
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFileName)) {
                FlatXmlDataSet.write(dataSet, new BufferedOutputStream(fileOutputStream, _outputBufferSize));
            }

            conn.close();
        } catch (DataSetException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DataSource getDataSource() {
        return _dataSource;
    }

    public IDatabaseConnection getDatabaseConnection() {
        Connection jdbcConnection;
        try {
            jdbcConnection = getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        IDatabaseConnection connection;
        try {
            connection = new DatabaseConnection(jdbcConnection, "public");
        } catch (DatabaseUnitException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public int getRowCountInDatabaseTable(String tableName) {
        ITable actualTable;

        IDatabaseConnection dbConn = getDatabaseConnection();

        int rowCount;

        try {
            IDataSet databaseDataSet = dbConn.createDataSet();

            actualTable = databaseDataSet.getTable(tableName);

            rowCount = actualTable.getRowCount();
        } catch (DataSetException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dbConn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return rowCount;
    }

    public void restoreSnapshot(IDataSet snapshot) {
        IDatabaseConnection connection = getDatabaseConnection();

        try {
            DatabaseOperation.DELETE_ALL.execute(connection, snapshot);
        } catch (DatabaseUnitException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setupDatabase(IDataSet initDataSet) throws Exception {
        IDatabaseConnection connection = getDatabaseConnection();

        DatabaseOperation.INSERT.execute(connection, initDataSet);

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
