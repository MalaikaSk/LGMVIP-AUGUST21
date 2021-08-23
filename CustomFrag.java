package com.example.snapfox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.EnumSet;
import java.util.Set;

public class CustomFrag extends ArFragment {

    @Override
    protected Config getSessionConfiguration(Session session)
    {
        Config c = new Config(session);
        c.setAugmentedFaceMode(Config.AugmentedFaceMode.MESH3D);
        this.getArSceneView().setupSession(session);

        return  c;

    }


    @Override
    protected Set<Session.Feature> getSessionFeatures()
    {
        //return super.getSessionFeatures();
        return EnumSet.of(Session.Feature.FRONT_CAMERA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);

        FrameLayout f = (FrameLayout) super.onCreateView(inflater,container,savedInstanceState);
        getPlaneDiscoveryController().hide();
        getPlaneDiscoveryController().setInstructionView(null);

        return  f;

    }








}
