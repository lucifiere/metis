package spider.constant

/**
 * Created by Tyler.Wang on 2016/12/19.
 */
class BuildingNumberInfo {

    public static Map buildingNumberPage = [:]
    public static Map buildingNumberExcel = [:]

    private BuildingNumberInfo() {}
    private static BuildingNumberInfo r

    public static BuildingNumberInfo getBuildingNumberInfo() {
        if (r == null) {
            r = new BuildingNumberInfo()
            return r
        }
        r
    }

    public static reset(){
        buildingNumberPage = [:]
        buildingNumberExcel = [:]
    }

}
