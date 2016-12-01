package spider.constant

/**
 *  Created by XD.Wang on 2016/12/1.
 */
class Condition {

    private static Condition c

    private String city = Pattern.TIAN_JIN
    private String district

    String getDistrict() {
        return district
    }

    void setDistrict(String district) {
        this.district = district
    }

    String getCity() {
        return city
    }

    void setCity(String city) {
        this.city = city
    }

    private Condition() {}

    public static Condition getCondition() {
        c == null ? new Condition() : c
    }
}
