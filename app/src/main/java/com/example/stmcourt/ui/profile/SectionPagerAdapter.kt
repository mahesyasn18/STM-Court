package com.example.stmcourt.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.stmcourt.ui.home.newtaste.HomeNewTasteFragment
import com.example.stmcourt.ui.home.popular.HomePopularFragment
import com.example.stmcourt.ui.home.recommended.HomeRecommendedFragment
import com.example.stmcourt.ui.profile.account.AccountFragment
import com.example.stmcourt.ui.profile.stmcourt.StmCourtFragment

class SectionPagerAdapter(fm:FragmentManager) :FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return  when(position){
            0 -> "Account"
            1 -> "STMCourt"
            else -> "  "
        }
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return  when(position){
            0 -> {
                fragment = AccountFragment()
                return fragment
            }
            1 -> {
                fragment = StmCourtFragment()
                return fragment
            }
            else -> {
                fragment = AccountFragment()
                return fragment
            }
        }
    }
}