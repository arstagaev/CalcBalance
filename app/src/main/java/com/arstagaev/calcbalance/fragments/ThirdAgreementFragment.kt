package com.arstagaev.calcbalance.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.arstagaev.calcbalance.R
import com.arstagaev.calcbalance.test.ScreenSlidePageFragment
import com.arstagaev.calcbalance.test.ScreenSlidePageFragment2
import com.arstagaev.calcbalance.test.ScreenSlidePageFragment3
import com.arstagaev.calcbalance.test.ZoomOutPageTransformer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdAgreementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdAgreementFragment : Fragment() {
    // TODO: Rename and change  types of parameters
    private var param1: String? = null
    private var param2: String? = null

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var viewPager: ViewPager2
    private lateinit var buttonToNext : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agreement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = view.findViewById(R.id.pager)
        buttonToNext = view.findViewById(R.id.next_from_three_fragment)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(requireActivity())
        viewPager.adapter = pagerAdapter

        viewPager.setPageTransformer(ZoomOutPageTransformer())
        buttonToNext.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_finishFragment)
        }

    }

    /**
     * A simple pager adapter that represents com.arstagaev.calcbalance.test.ScreenSlidePageFragment
     * objects, in sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {


        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ScreenSlidePageFragment()
                1 -> ScreenSlidePageFragment2()
                2 -> ScreenSlidePageFragment3()
                else -> ScreenSlidePageFragment()
            }
        }
    }

    companion object {

        /**
         * The number of pages (wizard steps) to show in this demo.
         */
        private const val NUM_PAGES = 3
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AgreementFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdAgreementFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}