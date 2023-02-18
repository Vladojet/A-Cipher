package com.example.aleo.presentation.education.fragments


import android.app.ActionBar.LayoutParams
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.aleo.R
import com.example.aleo.databinding.VideoDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideoDetails : Fragment() {

    private var videoId: String = "vemLEwjIxow"
    private lateinit var binding: VideoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoId = it.getString(ARG_LINK, videoId)
        }
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationBar).also {
            it.visibility = View.INVISIBLE
        }
        requireActivity().findViewById<FrameLayout>(R.id.fragmentHolder).also {
            it.layoutParams.height = LayoutParams.MATCH_PARENT
            it.layoutParams.width = LayoutParams.MATCH_PARENT
        }
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window?.decorView?.systemUiVisibility.also {
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION.and(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    companion object {
        fun newInstance(param1: String) =
            VideoDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_LINK, param1)
                }
            }
        private const val ARG_LINK = "link"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VideoDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePlayer()
        activity?.window?.decorView?.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    private fun preparePlayer() {
        val youTubePlayerView: YouTubePlayerView = binding.youtubePlayer
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.enterFullScreen()
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = videoId
                youTubePlayer.loadVideo(videoId, 0F)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationBar).also {
            it.visibility = View.VISIBLE
        }
        requireActivity().findViewById<FrameLayout>(R.id.fragmentHolder).also {
            val set = ConstraintSet()
            set.connect(
                R.id.fragmentHolder,
                ConstraintSet.BOTTOM,
                R.id.bottomNavigationBar,
                ConstraintSet.TOP
            )
        }
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window?.decorView?.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}











