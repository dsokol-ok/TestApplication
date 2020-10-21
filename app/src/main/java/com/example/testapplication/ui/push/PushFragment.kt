package com.example.testapplication.ui.push

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testapplication.MainActivity
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentPushBinding


class PushFragment : Fragment(), View.OnClickListener {

    private lateinit var pushViewModel: PushViewModel

    private lateinit var binding: FragmentPushBinding

    private val channelId = "CHANNEL_ID"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        pushViewModel = ViewModelProvider(this).get(PushViewModel::class.java)

        binding = FragmentPushBinding.inflate(inflater, container, false)

        binding.clickListener = this

        return binding.root
    }

    private fun createPush(text: String) {

        if (text.isBlank()) return

        val resultIntent = Intent(requireContext(), MainActivity::class.java)
        val resultPendingIntent = PendingIntent.getActivity(requireContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(requireContext(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.title_push))
                .setContentText(getString(R.string.body_push, text))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(resultPendingIntent)

        val notification: Notification = builder.build()
        val notificationManager = requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(channelId, getString(R.string.readable_title), NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(1, notification)

    }

    override fun onClick(v: View?) {
        if (v == null) return

        when (v.id) {
            R.id.launch_push_btn -> createPush(binding.pushEditText.text.toString())
        }
    }

}