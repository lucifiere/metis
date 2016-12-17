package spider.constant

/**
 *  Created by XD.Wang on 2016/12/1.
 */
class Condition {

    private static Condition c

    private String cityName = '天津'
    private String city = Pattern.TIAN_JIN
    private String district = ''
    private String ohDistrict = ''

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

    String getOhDistrict() {
        return ohDistrict
    }

    void setOhDistrict(String ohDistrict) {
        this.ohDistrict = ohDistrict
    }

    String getCityName() {
        return cityName
    }

    void setCityName(String cityName) {
        this.cityName = cityName
    }

    private Condition() {}

    public static Condition getCondition() {
        if (c == null) {
            c = new Condition()
            return c
        }
        c
    }
}
