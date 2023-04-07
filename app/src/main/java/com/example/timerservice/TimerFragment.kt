package com.example.timerservice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.timerservice.databinding.FragmentTimerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private var _binding : FragmentTimerBinding? = null
    private val binding get() = checkNotNull(_binding)

    private lateinit var timerIntent: Intent

    private var isRunning = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timerIntent = Intent(requireContext(), TimerService::class.java)
        setBtnClickListener()
    }

    private fun setBtnClickListener(){
        binding.btnPlay.setOnClickListener {
            isRunning = isRunning.not()
            if(isRunning){
                binding.btnPlay.setImageResource(R.drawable.baseline_pause_24)
                startTimerWithMode(TimerService.START)
            } else {
                binding.btnPlay.setImageResource(R.drawable.baseline_play_arrow_24)
                startTimerWithMode(TimerService.PAUSE)
            }
        }

        binding.btnReset.setOnClickListener {
            startTimerWithMode(TimerService.RESET)
            isRunning = false
        }
    }

    private fun startTimerWithMode(mode: String){
        timerIntent.putExtra(TimerService.MANAGE_ACTION_NAME, mode)
        requireActivity().startService(timerIntent)
        timerIntent.removeExtra(TimerService.MANAGE_ACTION_NAME)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}