package Repositories.Contracts;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IGenericRepo {
    public void printAll(ResultSet rs) throws SQLException;
}
