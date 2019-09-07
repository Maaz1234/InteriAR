package com.maaz.interiar.ui.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.maaz.interiar.ui.fragments.ProductDescriptionFragment;
import com.maaz.interiar.ui.fragments.ProductSpecificationFragment;

public class ProductDetailsAdapter extends FragmentPagerAdapter
{
    private int totalTabs;

    public ProductDetailsAdapter(FragmentManager fm, int totalTabs)
    {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i) {
            case 0:
                ProductDescriptionFragment productDescriptionFragment_1 = new ProductDescriptionFragment();
                return productDescriptionFragment_1;
            case 1:
                ProductSpecificationFragment productSpecificationFragment = new ProductSpecificationFragment();
                return productSpecificationFragment;
            case 2:
                ProductDescriptionFragment productDescriptionFragment_2 = new ProductDescriptionFragment();
                return productDescriptionFragment_2;


                default:
                    return null;
        }
    }

    @Override
    public int getCount()
    {
        return totalTabs;
    }
}
