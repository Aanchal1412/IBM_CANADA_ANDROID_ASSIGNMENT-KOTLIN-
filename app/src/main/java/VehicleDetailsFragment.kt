
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.rideskotlin.MainActivity
import com.example.rideskotlin.R


class VehicleDetailsFragment : Fragment() {

    private var vin: String? = null
    private var make_and_model: String? = null
    private var color: String? = null
    private var car_type: String? = null
    lateinit var toolbar_fragment: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tv_fetch_vin: TextView
        val tv_fetch_model: TextView
        val tv_fetch_color: TextView
        val tv_fetch_car_type: TextView

        val view: View = inflater.inflate(R.layout.fragment_vehicle_details, container, false)

        tv_fetch_vin = view.findViewById(R.id.tv_fetch_vin)
        tv_fetch_model = view.findViewById(R.id.tv_fetch_model)
        tv_fetch_color = view.findViewById(R.id.tv_fetch_color)
        tv_fetch_car_type = view.findViewById(R.id.tv_fetch_car_type)

        toolbar_fragment = view.findViewById(R.id.toolbar_fragment)
        (activity as MainActivity?)?.setSupportActionBar(toolbar_fragment)

        toolbar_fragment.title = "Vehicle Details"
        val bundle = arguments
        vin = bundle!!.getString("vin")
        make_and_model = bundle.getString("make_and_model")
        color = bundle.getString("color")
        car_type = bundle.getString("car_type")


        tv_fetch_vin.text = "Vin:  $vin"
        tv_fetch_model.text = "Make_and_Model:  $make_and_model"
        tv_fetch_color.text = "Color:  $color"
        tv_fetch_car_type.text = "Car_Type:  $car_type"
        setupOnBackPressed()
        return view
    }

    private fun setupOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    Toast.makeText(requireContext(), "Go Back", Toast.LENGTH_SHORT).show()
                    isEnabled = false
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().onBackPressed()
                }
            }
        })
    }
}