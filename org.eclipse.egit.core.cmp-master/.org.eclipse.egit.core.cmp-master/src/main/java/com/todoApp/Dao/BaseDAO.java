package com.todoApp.Dao;


import com.todoApp.config.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class BaseDAO {
    private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);
    /**
     * Dùng cho các câu lệnh INSERT, UPDATE, DELETE
     */
    protected boolean executeUpdate(String sql, Object... params) {
        Connection conn = DBConnection.getInstance().getConnection(); // không tự đóng!
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParameters(stmt, params);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error executing update:{}", sql, e);
            return false;
        }
    }


    /**
     * Dùng để lấy dữ liệu SELECT, truyền vào mapper để xử lý từng dòng ResultSet
     */
    protected <T> List<T> executeQuery(String sql, Function<ResultSet, T> mapper, Object... params) {
        List<T> resultList = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection(); // giữ mở

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParameters(stmt, params);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                T obj = mapper.apply(rs);
                if (obj != null) {
                    resultList.add(obj);
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query:{}", sql, e);
        }

        return resultList;
    }


    /**
     * Gán tham số vào PreparedStatement
     */
    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
        }
    }
}