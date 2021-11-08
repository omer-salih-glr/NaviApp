package com.omerglr.naviapp.ui.profilim

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.omerglr.naviapp.ui.mesaj.ProfilimFragment

class PageAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return FotograflarimFragment()
            }
            1 -> {
                return BilgilerimFragment()
            }

            else -> {
                return FotograflarimFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Fotograflarım"
            }
            1 -> {
                return "Bilgilerim"
            }

        }
        return super.getPageTitle(position)
    }

}