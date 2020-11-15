package icbm.classic.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTier3LauncherBottom extends ModelBase
{
    // fields
    ModelRenderer Shape12;
    ModelRenderer Shape13;

    public ModelTier3LauncherBottom()
    {
        textureWidth = 256;
        textureHeight = 256;

        Shape12 = new ModelRenderer(this, 0, 80);
        Shape12.addBox(0F, 0F, 0F, 11, 6, 6);
        Shape12.setRotationPoint(-5F, 18F, -6F);
        Shape12.setTextureSize(256, 256);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 0, 80);
        Shape13.addBox(0F, 0F, 0F, 11, 6, 6);
        Shape13.setRotationPoint(-5F, 18F, 1F);
        Shape13.setTextureSize(256, 256);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
    }

    public void render(float f5)
    {
        Shape12.render(f5);
        Shape13.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
