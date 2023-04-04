package com.example.stmcourt.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stmcourt.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_profile,container,false)
    return root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val sectionPageAdapter = SectionPagerAdapter(
      childFragmentManager
    )
    viewPager.adapter = sectionPageAdapter
    tabLayout.setupWithViewPager(viewPager)
  }


}