package com.lcabral.artseventh.featuresdashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.MovieNavigation
import com.lcabral.artseventh.core.common.navigation.PopularNavigation
import com.lcabral.artseventh.core.common.navigation.TrendingNavigation
import com.lcabral.artseventh.featuresdashboard.R
import com.lcabral.artseventh.featuresdashboard.databinding.FragmentDashboardBinding
import com.lcabral.artseventh.featuresdashboard.presentation.extensions.includeChild
import org.koin.android.ext.android.inject


class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val movieNavigation: MovieNavigation by inject()
    private val popularNavigation: PopularNavigation by inject()
    private val trendingNavigation: TrendingNavigation by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            with(binding) {
                includeChild(movieContainer.id,movieNavigation.create() )
                includeChild(popularContainer.id, popularNavigation.navigateToPopular())
                includeChild(trendingContainer.id, trendingNavigation.navigateToTrending())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): DashboardFragment = DashboardFragment()
    }
}
