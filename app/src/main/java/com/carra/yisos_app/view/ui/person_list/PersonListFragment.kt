package com.carra.yisos_app.view.ui.person_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.carra.yisos_app.databinding.FragmentPersonListBinding
import com.carra.yisos_app.view.ui.adapter.PersonListAdapter
import kotlinx.android.synthetic.main.fragment_person_list.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick

class PersonListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentPersonListBinding
    private lateinit var adapter: PersonListAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentPersonListBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@PersonListFragment).get(PersonListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchPersonList()
        setupAdapter()
        setupObservers()

        retry.onClick {
            viewDataBinding.viewmodel?.fetchPersonList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding.viewmodel?.personListLive?.removeObservers(viewLifecycleOwner)
        viewDataBinding.viewmodel?.toastMessage?.removeObservers(viewLifecycleOwner)
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.personListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updatePersonList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            activity?.longToast(it)
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = PersonListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = GridLayoutManager(activity, 2)
            person_list.layoutManager = layoutManager
            person_list.addItemDecoration(
                SpaceItemDecoration(
                    2,
                    8,
                    false,
                    0
                )
            )
            person_list.adapter = adapter
        }
    }
}