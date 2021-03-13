package com.example.ui_components.base

import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import com.example.ui_components.R
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CompositeIcon
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider

abstract class BaseYandexMapFragment : BaseFragment(), UserLocationObjectListener {

    protected var mapView: MapView? = null
    protected var userLocationLayer: UserLocationLayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(requireContext())
    }


    fun setUserLocation() {
        val mapKit = MapKitFactory.getInstance()
        userLocationLayer = mapKit.createUserLocationLayer(mapView?.mapWindow!!)
        userLocationLayer?.isVisible = true
        userLocationLayer?.isHeadingEnabled = true
        userLocationLayer?.setObjectListener(this)

    }

    fun onStopMap(mapView: MapView) {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    fun onStartMap(mapView: MapView) {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {

        userLocationLayer!!.setAnchor(
            PointF((mapView!!.width * 0.5).toFloat(), (mapView!!.height * 0.5).toFloat()),
            PointF((mapView!!.width * 0.5).toFloat(), (mapView!!.height * 0.83).toFloat())
        )

        userLocationView.arrow.setIcon(
            ImageProvider.fromResource(
                requireContext(), R.drawable.yandex_logo_en
            )
        )

        val pinIcon: CompositeIcon = userLocationView.getPin().useCompositeIcon()

        pinIcon.setIcon(
            "icon",
            ImageProvider.fromResource(requireContext(), R.drawable.yandex_logo_ru),
            IconStyle().setAnchor(PointF(0f, 0f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(0f)
                .setScale(1f)
        )

        pinIcon.setIcon(
            "pin",
            ImageProvider.fromResource(requireContext(), R.drawable.yandex_logo_ru),
            IconStyle().setAnchor(PointF(0.5f, 0.5f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(1f)
                .setScale(0.5f)
        )

        userLocationView.accuracyCircle.fillColor = Color.BLUE

    }

    override fun onObjectRemoved(p0: UserLocationView) {

    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
    }
}