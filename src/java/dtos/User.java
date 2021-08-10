
package dtos;

public class User {
    private String userID;
    private String userName;
    private String email;
    private String phoneNumber;
    private String imageURL;
    private String password;
    private String createDate;
    private int rank;
    private boolean status;
    private boolean statusPromotion;
    private int roleID;
    private String roleName;
    private String promotionDate;

    public User(String userID, String userName, String email, String phoneNumber, String imageURL, String password, String createDate, int rank, boolean status, boolean statusPromotion, int roleID, String roleName, String promotionDate) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.password = password;
        this.createDate = createDate;
        this.rank = rank;
        this.status = status;
        this.statusPromotion = statusPromotion;
        this.roleID = roleID;
        this.roleName = roleName;
        this.promotionDate = promotionDate;
    }

    public User(String userID, String userName, String email, String phoneNumber, String imageURL, String password, String createDate, int rank, boolean status, boolean statusPromotion, int roleID) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.password = password;
        this.createDate = createDate;
        this.rank = rank;
        this.status = status;
        this.statusPromotion = statusPromotion;
        this.roleID = roleID;
    }

    public User(String userID, String userName, String email, String phoneNumber, String imageURL, String password) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.password = password;
    }

    public User(String userID, String userName, int roleID) {
        this.userID = userID;
        this.userName = userName;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatusPromotion() {
        return statusPromotion;
    }

    public void setStatusPromotion(boolean statusPromotion) {
        this.statusPromotion = statusPromotion;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(String promotionDate) {
        this.promotionDate = promotionDate;
    }

}
