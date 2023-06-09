package com.bedirhandroid.pokemontechcase.ui.second.fragments.pokemon.detail

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bedirhandroid.pokemontechcase.R
import com.bedirhandroid.pokemontechcase.base.BaseFragment
import com.bedirhandroid.pokemontechcase.base.ErrorMessages
import com.bedirhandroid.pokemontechcase.databinding.FragmentDetailBinding
import com.bedirhandroid.pokemontechcase.util.Constant.KEY_DATA
import com.bedirhandroid.pokemontechcase.util.loadImage

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    private var detailsId: Int? = null
    //window manager for float view
    private val windowManager by lazy {
        requireActivity().getSystemService(Context.WINDOW_SERVICE)
                as WindowManager
    }
    //floatView
    private lateinit var floatView: View
    //floatView params
    private lateinit var paramFloat: WindowManager.LayoutParams

    override fun initView() {
        //getArguments
        detailsId = arguments?.getInt(KEY_DATA, 0)
        viewModelScope {
            detailsId?.let(::getPokemonDetails) ?: kotlin.run {
                viewModel.errorLiveData.postValue(ErrorMessages.ERROR)
            }
        }
        initOverlayLayout()
    }

    override fun initListeners() {
        viewBindingScope {
            floatView.apply {
                findViewById<Button>(R.id.btn_close_popup).setOnClickListener {
                    try {
                        //dismiss floatView
                        windowManager.removeView(floatView)
                    } catch (exc: Error) {
                        //catch error
                        viewModel.errorLiveData.postValue(ErrorMessages.ERROR_WINDOW_MANAGER)
                    }
                }
            }
            btnOverlay.setOnClickListener {
                drawOverlayScreen()
                try {
                    //update if floatView is open
                    windowManager.updateViewLayout(floatView, paramFloat)
                } catch (exc: Exception) {
                    //addItem if floatView is not init
                    windowManager.addView(floatView, paramFloat)
                }
            }
        }
    }

    private fun drawOverlayScreen() {
        //set data floatView views
        floatView.apply {
            viewModel.pokemonLiveData.value?.let { _data ->
                _data.sprites?.frontImage?.let {
                    findViewById<ImageView>(R.id.iv_front).loadImage(it)
                }
                _data.sprites?.backImage?.let {
                    findViewById<ImageView>(R.id.iv_back).loadImage(it)
                }
                _data.name?.let {
                    findViewById<TextView>(R.id.tv_name).text = it
                }
            }
        }
    }

    override fun initObservers() {
        //observe live data
        viewModelScope {
            pokemonLiveData.observe(this@DetailFragment) {
                viewBindingScope {
                    tvPokeName.text = getString(R.string.pokemon_name, it.name)
                    tvPokeHeight.text = getString(R.string.pokemon_height, it.height)
                    tvPokeWeight.text = getString(R.string.pokemon_weight, it.weight)
                    btnOverlay.text = getString(R.string.pokemon_btn_name, it.name)
                    it.sprites?.frontImage?.let (ivPokemonDetails::loadImage)
                }
            }
        }
    }

    private fun initOverlayLayout() {
        //init floatView views
        floatView =
            (requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.layout_overlay_popup,
                null,
                false
            )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //floatView params init
            paramFloat = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
        }
        paramFloat.gravity = Gravity.CENTER or Gravity.CENTER_HORIZONTAL
    }

}