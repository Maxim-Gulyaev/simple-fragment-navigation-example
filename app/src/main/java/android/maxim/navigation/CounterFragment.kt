package android.maxim.navigation

import android.maxim.navigation.databinding.FragmentCounterBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CounterFragment : Fragment() {

    private lateinit var binding: FragmentCounterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCounterBinding.inflate(inflater, container, false)

        binding.counterTextView.text = getString(R.string.screen_label, getCounterValue())
        binding.quoteTextView.text = getQuote()

        binding.btnLaunchNext.setOnClickListener { launchNext() }
        binding.btnGoBack.setOnClickListener { goBack() }

        return binding.root
    }

    private fun launchNext() {
        val fragment = CounterFragment.newInstance(
            counterValue = (requireActivity() as HelloWorldActivity).getScreenCount() + 1,
            quote = (requireActivity() as HelloWorldActivity).createQuote()
        )
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun goBack() {
        requireActivity().onBackPressed()
    }

    private fun getCounterValue(): Int = requireArguments().getInt(ARG_COUNTER_VALUE)

    private fun getQuote(): String = requireArguments().getString(ARG_QUOTE)!!

    companion object {

        @JvmStatic
        private val ARG_COUNTER_VALUE = "ARG_COUNTER_VALUE"

        @JvmStatic
        private val ARG_QUOTE = "ARG_QUOTE"

        @JvmStatic
        fun newInstance(counterValue: Int, quote: String): CounterFragment {
            val args = Bundle().apply {
                putInt(ARG_COUNTER_VALUE, counterValue)
                putString(ARG_QUOTE, quote)
            }
            val fragment = CounterFragment()
            fragment.arguments = args
            return fragment
        }

    }
}