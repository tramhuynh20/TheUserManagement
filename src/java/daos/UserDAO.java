package daos;

import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import utils.DBUtils;

public class UserDAO {

    Vector<User> listUser = new Vector<User>();

    public Vector<User> getAllUser() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listUser.clear();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID, userName, email, phoneNumber, imageURL, password, "
                        + "createDate, rank, status, statusPromotion, [User].roleID, roleName, promotionDate "
                        + "FROM [User] "
                        + "INNER JOIN Role ON [User].roleID=Role.roleID";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listUser.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"),
                            rs.getString("phoneNumber"), rs.getString("imageURL"), rs.getString("password"), rs.getString("createDate"),
                            rs.getInt("rank"), rs.getBoolean("status"), rs.getBoolean("statusPromotion"), rs.getInt("roleID"), rs.getString("roleName"),
                            rs.getString("promotionDate")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public Vector<User> getAllSub() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listUser.clear();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID, userName, email, phoneNumber, "
                        + "imageURL, password, createDate, rank, status, "
                        + "statusPromotion, roleID FROM [User] WHERE roleID=2";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listUser.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"),
                            rs.getString("phoneNumber"), rs.getString("imageURL"), rs.getString("password"), rs.getString("createDate"),
                            rs.getInt("rank"), rs.getBoolean("status"), rs.getBoolean("statusPromotion"), rs.getInt("roleID")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public Vector<User> getAllAdmin() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listUser.clear();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID, userName, email, phoneNumber, "
                        + "imageURL, password, createDate, rank, status, "
                        + "statusPromotion, roleID FROM [User] WHERE roleID=1";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    listUser.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"),
                            rs.getString("phoneNumber"), rs.getString("imageURL"), rs.getString("password"), rs.getString("createDate"),
                            rs.getInt("rank"), rs.getBoolean("status"), rs.getBoolean("statusPromotion"), rs.getInt("roleID")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public User checkLogin(String userID, String password) throws SQLException {
        Connection conn = null;
        User result = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT userID,userName,roleID FROM [User] WHERE status='True' AND userID=? AND password=?";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    int roleID = rs.getInt("roleID");
                    result = new User(userID, userName, roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public User getUserByID(String userID) throws SQLException {
        getAllUser();
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserID().equalsIgnoreCase(userID)) {
                return listUser.get(i);
            }
        }
        return null;
    }

    public List<User> searchUserByName(String word) throws SQLException, NamingException {
        List<User> list = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = DBUtils.getConnection();
        if (conn != null) {
            String sql = "SELECT userID, userName, email, phoneNumber, "
                    + "imageURL, password, createDate, rank, status, "
                    + "statusPromotion, roleID FROM [User]"
                    + "Where userName like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + word + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"),
                        rs.getString("phoneNumber"), rs.getString("imageURL"), rs.getString("password"), rs.getString("createDate"),
                        rs.getInt("rank"), rs.getBoolean("status"), rs.getBoolean("statusPromotion"), rs.getInt("roleID")));
            }
        }
        return list;
    }

    public boolean createUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Insert [User] Values(?,?,?,?,?,?,?,?,?,?,NULL,?)";

                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getUserID());
                ps.setString(2, user.getUserName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPhoneNumber());
                ps.setString(5, user.getImageURL());
                ps.setString(6, user.getPassword());
                ps.setString(7, user.getCreateDate());
                ps.setInt(8, user.getRank());
                ps.setBoolean(9, user.isStatus());
                ps.setBoolean(10, user.isStatusPromotion());
                ps.setInt(11, user.getRoleID());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return true;
    }

    public boolean updateUser(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE [User] SET userName=?, email=?, phoneNumber=?, imageURL=?, password=? \n"
                + "WHERE userID=?";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPhoneNumber());
                ps.setString(4, user.getImageURL());
                ps.setString(5, user.getPassword());
                ps.setString(6, user.getUserID());
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean addPromotion(String userID, String date) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "UPDATE [User] SET statusPromotion='True', rank=5, promotionDate=? "
                        + "WHERE userID=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, date);
                ps.setString(2, userID);

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return check;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return true;
    }

    public Vector<User> getAllUserHavePromotion() throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID, userName, email, phoneNumber, imageURL, password, "
                        + "createDate, rank, status, statusPromotion, [User].roleID, roleName, promotionDate "
                        + "FROM [User] "
                        + "INNER JOIN Role ON [User].roleID=Role.roleID "
                        + "WHERE statusPromotion='True'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    listUser.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("email"),
                            rs.getString("phoneNumber"), rs.getString("imageURL"), rs.getString("password"), rs.getString("createDate"),
                            rs.getInt("rank"), rs.getBoolean("status"), rs.getBoolean("statusPromotion"), rs.getInt("roleID"), rs.getString("roleName"),
                            rs.getString("promotionDate")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public boolean updateStatus(String userID) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [User] "
                        + "SET status = 'False' "
                        + "WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

    public boolean updatePromotion(String userID, int rank) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [User] "
                        + "SET rank = ? "
                        + "WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, rank);
                ps.setString(2, userID);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

    public boolean updateDeletePromotion(String userID) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [User] "
                        + "SET statusPromotion = 'False', rank=0 "
                        + "WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }
}
