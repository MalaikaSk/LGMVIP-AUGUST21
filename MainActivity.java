package com.example.snapfox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.ar.core.AugmentedFace;
import com.google.ar.core.Frame;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.AugmentedFaceNode;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {
     private ModelRenderable m;
     private Texture t;
     private boolean iadd = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomFrag cf = (CustomFrag) getSupportFragmentManager().findFragmentById(R.id.frag1);

        ModelRenderable.builder()
                .setSource(this, R.raw.fox_face)
                .build()
                .thenAccept(render ->
                {

                    m = render;
                    m.setShadowCaster(false);
                    m.setShadowReceiver(false);
                });

        Texture.builder()
                .setSource(this, R.drawable.fox_face_mesh_texture)
                .build()
                .thenAccept(t -> this.t = t);


        cf.getArSceneView().setCameraStreamRenderPriority(Renderable.RENDER_PRIORITY_FIRST);
        cf.getArSceneView().getScene().addOnUpdateListener(frameTime ->
                {
                    if (m == null || t ==null)
                        return;

                    Frame ff = cf.getArSceneView().getArFrame();
                    Collection<AugmentedFace> ca = ff.getUpdatedTrackables(AugmentedFace.class);

                    for (AugmentedFace af : ca)
                    {
                        if (iadd)
                            return;

                        AugmentedFaceNode afn = new AugmentedFaceNode(af);
                        afn.setParent(cf.getArSceneView().getScene());
                        afn.setFaceRegionsRenderable(m);
                        afn.setFaceMeshTexture(t);

                        iadd = true;



                    }

                }

                );






    }
}