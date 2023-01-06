import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DataModel {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("uid")
    @Expose
    var uid: String? = null

    @SerializedName("vin")
    @Expose
    var vin: String? = null

    @SerializedName("make_and_model")
    @Expose
    var makeAndModel: String? = null

    @SerializedName("color")
    @Expose
    var color: String? = null

    @SerializedName("transmission")
    @Expose
    var transmission: String? = null

    @SerializedName("drive_type")
    @Expose
    var driveType: String? = null

    @SerializedName("fuel_type")
    @Expose
    var fuelType: String? = null

    @SerializedName("car_type")
    @Expose
    var carType: String? = null

    @SerializedName("car_options")
    @Expose
    var carOptions: List<String>? = null

    @SerializedName("specs")
    @Expose
    var specs: List<String>? = null

    @SerializedName("doors")
    @Expose
    var doors: Int? = null

    @SerializedName("mileage")
    @Expose
    var mileage: Int? = null

    @SerializedName("kilometrage")
    @Expose
    var kilometrage: Int? = null

    @SerializedName("license_plate")
    @Expose
    var licensePlate: String? = null
}



