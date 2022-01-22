package com.bcebhagalpur.bcece.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bcebhagalpur.bcece.R
import kotlinx.android.synthetic.main.fragment_1.*

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtFragmentName.text="Fragment One"
    }

}
