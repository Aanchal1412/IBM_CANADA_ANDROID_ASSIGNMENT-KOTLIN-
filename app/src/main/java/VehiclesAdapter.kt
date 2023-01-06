import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rideskotlin.R

class VehiclesAdapter(
    var context: Context,
    var datamodel: List<DataModel>

) :
    RecyclerView.Adapter<VehiclesAdapter.VHolder>() {
    var model: DataModel? = null
    lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.vehicle_details, parent, false)
        return VHolder(view)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        model = datamodel[position]
        holder.tv_count.text = "" + (position + 1).toString()
        holder.tv_vin.text = "Vin:  " + model!!.vin
        holder.tv_make_and_model.text = "Make-and-model:  " + model!!.makeAndModel
        holder.itemView.setOnClickListener {
            if (itemClickListener != null) {

                itemClickListener.onItemClick(model, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return datamodel.size
    }

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_vin: TextView
        var tv_make_and_model: TextView
        var tv_count: TextView

        init {
            tv_vin = itemView.findViewById(R.id.tv_vin)
            tv_make_and_model = itemView.findViewById(R.id.tv_make_and_model)
            tv_count = itemView.findViewById(R.id.tv_count)
        }
    }

    fun setOnCLickListener(onClickListener: ItemClickListener) {
        this.itemClickListener = onClickListener

    }

<<<<<<< HEAD
=======

>>>>>>> ced7f2fa29a875f88f36efbc96976e233646e8cb
    interface ItemClickListener {
        fun onItemClick(dataModel: DataModel?, position: Int)
    }
}
