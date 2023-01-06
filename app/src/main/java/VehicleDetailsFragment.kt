
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
<<<<<<< HEAD
    lateinit var toolbar_fragment: Toolbar
=======
    var kilometrage = 0
    var remaining_kilometrage = 0
    var carbon_emission = 0.0
    var remaining_carbon_emission = 0.0
    var total_carbon_emission = 0.0
    lateinit var toolbar_fragment: Toolbar
    lateinit var btn_carbon_emission: Button
    lateinit var ll_carbon_emission: LinearLayout
>>>>>>> ced7f2fa29a875f88f36efbc96976e233646e8cb
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
<<<<<<< HEAD

        val view: View = inflater.inflate(R.layout.fragment_vehicle_details, container, false)

=======
        val tv_kilometres: TextView
        val tv_carbon_emission: TextView
        val view: View = inflater.inflate(R.layout.fragment_vehicle_details, container, false)
        // Inflate the layout for this fragment
>>>>>>> ced7f2fa29a875f88f36efbc96976e233646e8cb
        tv_fetch_vin = view.findViewById(R.id.tv_fetch_vin)
        tv_fetch_model = view.findViewById(R.id.tv_fetch_model)
        tv_fetch_color = view.findViewById(R.id.tv_fetch_color)
        tv_fetch_car_type = view.findViewById(R.id.tv_fetch_car_type)
<<<<<<< HEAD

        toolbar_fragment = view.findViewById(R.id.toolbar_fragment)
=======
        tv_kilometres = view.findViewById(R.id.tv_kilometres)
        tv_carbon_emission = view.findViewById(R.id.tv_carbon_emission)
        btn_carbon_emission = view.findViewById(R.id.btn_carbon_emission)
        toolbar_fragment = view.findViewById(R.id.toolbar_fragment)
        ll_carbon_emission = view.findViewById(R.id.ll_carbon_emission)
>>>>>>> ced7f2fa29a875f88f36efbc96976e233646e8cb
        (activity as MainActivity?)?.setSupportActionBar(toolbar_fragment)

        toolbar_fragment.title = "Vehicle Details"
        val bundle = arguments
        vin = bundle!!.getString("vin")
        make_and_model = bundle.getString("make_and_model")
        color = bundle.getString("color")
        car_type = bundle.getString("car_type")
<<<<<<< HEAD


=======
        kilometrage = bundle.getInt("kilometrage")
        btn_carbon_emission.setOnClickListener(View.OnClickListener {
            ll_carbon_emission.setVisibility(
                View.VISIBLE
            )
        })
        if (kilometrage > 5000) {
            carbon_emission = carbon_emission + 1 * 5000
            remaining_kilometrage = kilometrage - 5000
            remaining_carbon_emission = remaining_carbon_emission + 1.5 * remaining_kilometrage
            total_carbon_emission = carbon_emission + remaining_carbon_emission
        } else if (kilometrage <= 5000) {
            carbon_emission = carbon_emission + 1 * kilometrage
            total_carbon_emission = carbon_emission
        }
>>>>>>> ced7f2fa29a875f88f36efbc96976e233646e8cb
        tv_fetch_vin.text = "Vin:  $vin"
        tv_fetch_model.text = "Make_and_Model:  $make_and_model"
        tv_fetch_color.text = "Color:  $color"
        tv_fetch_car_type.text = "Car_Type:  $car_type"
<<<<<<< HEAD
=======
        tv_kilometres.text = "" + kilometrage
        tv_carbon_emission.text = "" + total_carbon_emission
>>>>>>> ced7f2fa29a875f88f36efbc96976e233646e8cb
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