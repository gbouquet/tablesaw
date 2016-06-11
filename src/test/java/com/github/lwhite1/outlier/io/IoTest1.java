package com.github.lwhite1.outlier.io;

import com.github.lwhite1.outlier.api.QueryHelper;
import com.github.lwhite1.outlier.columns.Column;
import com.github.lwhite1.outlier.api.ColumnType;
import com.github.lwhite1.outlier.Table;
import org.junit.Test;

import static com.github.lwhite1.outlier.api.ColumnType.*;
/**
 *
 */
public class IoTest1 {

  @Test
  public void testWithBusData() throws Exception {
    // Read the CSV file
    ColumnType[] types = {INTEGER, CATEGORY, CATEGORY, FLOAT, FLOAT};
    Table table = CsvReader.read(types, "data/bus_stop_test.csv");

    // Look at the column names
    print(table.columnNames());

    print(table.head(3).print());

    table = table.sortDescendingOn("stop_id");
    print(table.head(3).print());
    table.removeColumns("stop_desc");
    print(table.columnNames());

    Column c = table.floatColumn("stop_lat");

    print(table.floatColumn("stop_lon").describe());

    Table v = table.selectIf(QueryHelper.column("stop_lon").isGreaterThan(-0.1f));
    print(v.print());
    print(v.rowCount());
  }

  @Test
  public void testWithBushData() throws Exception {
    // Read the CSV file
    ColumnType[] types = {LOCAL_DATE, INTEGER, CATEGORY};
    Table table = CsvReader.read(types, "data/BushApproval.csv");

    // Look at the column names
    print(table.columnNames());

    print(table.print());
    print(table.rowCount());
  }

  private void print(Object o) {
    System.out.println(o);
  }
}