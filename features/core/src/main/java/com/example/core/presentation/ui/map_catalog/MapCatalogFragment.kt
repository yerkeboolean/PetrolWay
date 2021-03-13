package com.example.core.presentation.ui.map_catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.core.R
import com.example.core.databinding.FragmentMapCatalogBinding
import com.example.core.presentation.ui.items.PetrolSmallInfoUi
import com.example.ui_components.base.BaseYandexMapFragment
import com.example.ui_components.utils.gone
import com.example.ui_components.utils.visible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yandex.mapkit.Animation
import com.yandex.mapkit.map.CameraPosition
import kotlinx.android.synthetic.main.content_petrols_bottom_sheet.view.*
import kotlinx.android.synthetic.main.fragment_map_catalog.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapCatalogFragment : BaseYandexMapFragment() {
    private var zoom = 14.0f
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private val mapCatalogViewModel : MapCatalogViewModel by viewModel()
    private val allPetrolsGroupAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var binding : FragmentMapCatalogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapCatalogBinding.inflate(inflater, container, false).apply {
            viewModel = mapCatalogViewModel
            lifecycleOwner = this@MapCatalogFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = map_view
        setUserLocation()
        initOnClickListeners()
        initBottomSheetBehaviour()
        initAllPetrolsRecyclerView()
    }

    private fun initAllPetrolsRecyclerView() {
        binding.layoutBottomBehavior.rv_all_petrols.apply {
            adapter = allPetrolsGroupAdapter
        }
    }

    private fun initOnClickListeners() {
        ic_go_current_location.setOnClickListener {
            goCurrentLocation()
        }
        ic_zoom_in.setOnClickListener {
            changeZoomOfMap(0.5f)
        }
        ic_zoom_out.setOnClickListener {
            changeZoomOfMap(-0.5f)
        }
        tv_all_petrols.setOnClickListener {
            mapCatalogViewModel.requestAllPetrols()
        }
    }

    private fun goCurrentLocation() {
        zoom = 14f
        userLocationLayer?.cameraPosition()?.target
        mapView?.map?.move(
            CameraPosition(userLocationLayer?.cameraPosition()?.target!!, zoom, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 1f),
            null
        )

    }

    private fun changeZoomOfMap(zoomChanging: Float) {
        zoom += zoomChanging
        mapView?.map?.move(
            CameraPosition(mapView?.map?.cameraPosition?.target!!, zoom, 0.0f, 0.0f)
        )
    }

    private fun initBottomSheetBehaviour() {
        bottomSheetBehavior =
            BottomSheetBehavior.from(binding.layoutBottomBehavior)
        bottomSheetBehavior.isHideable = true
        val metrics = resources.displayMetrics
        bottomSheetBehavior.peekHeight = metrics.heightPixels / 2
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        ll_icons.gone()
                        tv_all_petrols.gone()
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        ll_icons.gone()
                        tv_all_petrols.gone()
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        ll_icons.visible()
                        tv_all_petrols.visible()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset > 0.5f) {
                    ll_icons.gone()
                }
            }

        })
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onStart() {
        super.onStart()
        onStartMap(map_view)

        mapCatalogViewModel.allPetrols.observe(viewLifecycleOwner, Observer (::handleAllPetrols))
    }

    private fun handleAllPetrols(data: List<PetrolSmallInfoUi>) {
        allPetrolsGroupAdapter.clear()
        allPetrolsGroupAdapter.addAll(data)
        tv_all_petrols.gone()
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    override fun onStop() {
        super.onStop()
        onStopMap(map_view)
    }

}

