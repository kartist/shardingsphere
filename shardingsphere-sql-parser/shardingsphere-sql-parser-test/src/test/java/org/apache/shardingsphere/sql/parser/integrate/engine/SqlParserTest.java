/*
 * credigo.com Inc.
 * Copyright (c) 2018-2020 All Rights Reserved.
 */
package org.apache.shardingsphere.sql.parser.integrate.engine;

import org.apache.shardingsphere.sql.parser.SQLParserEngineFactory;
import org.apache.shardingsphere.sql.parser.sql.statement.SQLStatement;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xbkaishui
 * @version $Id: SqlParserTest.java,  2020-07-15 5:17 PM xbkaishui Exp $$
 */
public class SqlParserTest {
    
    @Test
    public void testEmoji() throws Exception {
        System.out.println("汉".length());
        String str = "璐小果\uD83C\uDF4E我\uD83D\uDE00\uD869\uDEA5徐学习學習~\uD83D\uDE4B\u200D♀️\uD835\uDCD2\uD835\uDCF8\uD835\uDCEC\uD835\uDCEA\uD835\uDCD2\uD835\uDCF8\uD835\uDCF5\uD835\uDCEAasdfas!@#$%^123123d,.";
        System.out.println("璐小果\uD83C\uDF4E".length());
        int codePoint = (Character.codePointAt(str, 3));
        int count = Character.charCount(codePoint);
        System.out.println(str.charAt(3));
        System.out.println(count);
        System.out.println(str.length());
        String databaseType = "MySQL";
        String sql = "INSERT INTO t_user_third_account ( third_account_id,nickname)values(10,'璐小果\uD83C\uDF4E我\uD83D\uDE00\uD869\uDEA5徐学习學習~\uD83D\uDE4B\u200D♀️\uD835\uDCD2\uD835\uDCF8\uD835\uDCEC\uD835\uDCEA\uD835\uDCD2\uD835\uDCF8\uD835\uDCF5\uD835\uDCEAasdfas!@#$%^123123d,.')";
        SQLStatement actual = SQLParserEngineFactory.getSQLParserEngine(databaseType).parse(sql, false);
        Assert.assertNotNull(actual);
    }
}
