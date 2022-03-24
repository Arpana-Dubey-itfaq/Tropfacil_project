package com.tropfacil.ui.nav.exercices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tropfacil.databinding.FragmentExerciseInfoBinding
import com.tropfacil.model.exercices.ExercicesInfoList
import com.tropfacil.ui.nav.exercices.adapter.ExercicesInfoAdapter
import com.tropfacil.util.Constants.Companion.EXERCICES_INFO_LIST
import com.tropfacil.utils.ItemClickListener

class ExerciseInfoFragment : Fragment() {
    lateinit var binding: FragmentExerciseInfoBinding
    private var exercicesInfoList: ExercicesInfoList?= null
    private var exercicesInfoAdapter:ExercicesInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.let {
            if (it.getSerializable(EXERCICES_INFO_LIST) != null) {
                exercicesInfoList =
                    it.getSerializable(EXERCICES_INFO_LIST) as ExercicesInfoList
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentExerciseInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()
    }

    private fun setUI() {
        exercicesInfoAdapter = ExercicesInfoAdapter(requireContext(), object :ItemClickListener{
            override fun onItemClick(bundle: Bundle?) {

            }
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = exercicesInfoAdapter

        if(exercicesInfoList?.exercices?.isNotEmpty() == true)
            exercicesInfoAdapter?.updateList(exercicesInfoList?.exercices?:ArrayList())
    }

}