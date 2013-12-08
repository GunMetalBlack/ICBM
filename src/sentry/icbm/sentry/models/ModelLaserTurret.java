package icbm.sentry.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLaserTurret extends ModelBase
{
    // fields
    ModelRenderer basePlate;
    ModelRenderer leftMount;
    ModelRenderer rightMount;
    ModelRenderer body;
    ModelRenderer bodyTop;
    ModelRenderer bodyRight;
    ModelRenderer bodyLeft;
    ModelRenderer leftBarrel;
    ModelRenderer rightBarrel;
    ModelRenderer l1;
    ModelRenderer l2;
    ModelRenderer l3;
    ModelRenderer l4;
    ModelRenderer l5;
    ModelRenderer r1;
    ModelRenderer r2;
    ModelRenderer r3;
    ModelRenderer r4;
    ModelRenderer r5;
    ModelRenderer lCap;
    ModelRenderer rCap;
    ModelRenderer Hat;
    ModelRenderer LowerHat;
    ModelRenderer BatteryPack;
    ModelRenderer MiddleWire;
    ModelRenderer BatWire;
    ModelRenderer HatWire;
    ModelRenderer Details;
    ModelRenderer RightFootStrength;
    ModelRenderer LeftFootStrength;
    ModelRenderer FrontDetail;
    ModelRenderer LeftFootBase;
    ModelRenderer RightFootBase;
    ModelRenderer lEar1;
    ModelRenderer lEar2;
    ModelRenderer rEar1;
    ModelRenderer rEar2;

    public ModelLaserTurret()
    {
        textureWidth = 128;
        textureHeight = 128;

        basePlate = new ModelRenderer(this, 0, 67);
        basePlate.addBox(-5.5F, 0F, -5.5F, 11, 1, 11);
        basePlate.setRotationPoint(0F, 23F, 0F);
        basePlate.setTextureSize(128, 128);
        basePlate.mirror = true;
        setRotation(basePlate, 0F, 0F, 0F);
        leftMount = new ModelRenderer(this, 0, 53);
        leftMount.addBox(4F, -8F, -2F, 1, 8, 4);
        leftMount.setRotationPoint(0F, 23F, 0F);
        leftMount.setTextureSize(128, 128);
        leftMount.mirror = true;
        setRotation(leftMount, 0F, 0F, 0F);
        rightMount = new ModelRenderer(this, 11, 53);
        rightMount.addBox(-5F, -8F, -2F, 1, 8, 4);
        rightMount.setRotationPoint(0F, 23F, 0F);
        rightMount.setTextureSize(128, 128);
        rightMount.mirror = true;
        setRotation(rightMount, 0F, 0F, 0F);
        body = new ModelRenderer(this, 0, 37);
        body.addBox(-3F, -2.8F, -4F, 6, 5, 9);
        body.setRotationPoint(0F, 17F, 0F);
        body.setTextureSize(128, 128);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        bodyTop = new ModelRenderer(this, 36, 51);
        bodyTop.addBox(-2F, -4F, -3F, 4, 2, 7);
        bodyTop.setRotationPoint(0F, 17F, 0F);
        bodyTop.setTextureSize(128, 128);
        bodyTop.mirror = true;
        setRotation(bodyTop, 0F, 0F, 0F);
        bodyRight = new ModelRenderer(this, 31, 37);
        bodyRight.addBox(-4F, -2.5F, -3F, 1, 4, 7);
        bodyRight.setRotationPoint(0F, 17F, 0F);
        bodyRight.setTextureSize(128, 128);
        bodyRight.mirror = true;
        setRotation(bodyRight, 0F, 0F, 0F);
        bodyLeft = new ModelRenderer(this, 48, 37);
        bodyLeft.addBox(3F, -2.5F, -3F, 1, 4, 7);
        bodyLeft.setRotationPoint(0F, 17F, 0F);
        bodyLeft.setTextureSize(128, 128);
        bodyLeft.mirror = true;
        setRotation(bodyLeft, 0F, 0F, 0F);
        leftBarrel = new ModelRenderer(this, 29, 81);
        leftBarrel.addBox(2F, -1F, -17F, 1, 1, 13);
        leftBarrel.setRotationPoint(0F, 17F, 0F);
        leftBarrel.setTextureSize(128, 128);
        leftBarrel.mirror = true;
        setRotation(leftBarrel, 0F, 0F, 0F);
        rightBarrel = new ModelRenderer(this, 0, 81);
        rightBarrel.addBox(-3F, -1F, -17F, 1, 1, 13);
        rightBarrel.setRotationPoint(0F, 17F, 0F);
        rightBarrel.setTextureSize(128, 128);
        rightBarrel.mirror = true;
        setRotation(rightBarrel, 0F, 0F, 0F);
        l1 = new ModelRenderer(this, 1, 14);
        l1.addBox(0.9F, -2F, -6F, 3, 3, 3);
        l1.setRotationPoint(0F, 17F, 0F);
        l1.setTextureSize(128, 128);
        l1.mirror = true;
        setRotation(l1, 0F, 0F, 0F);
        l2 = new ModelRenderer(this, 9, 28);
        l2.addBox(-1.5F, -1.5F, 0F, 3, 3, 1);
        l2.setRotationPoint(2.5F, 16.5F, -8F);
        l2.setTextureSize(128, 128);
        l2.mirror = true;
        setRotation(l2, 0F, 0F, 0F);
        l3 = new ModelRenderer(this, 9, 28);
        l3.addBox(-1.5F, -1.5F, 0F, 3, 3, 1);
        l3.setRotationPoint(2.5F, 16.5F, -10F);
        l3.setTextureSize(128, 128);
        l3.mirror = true;
        setRotation(l3, 0F, 0F, 0F);
        l4 = new ModelRenderer(this, 9, 28);
        l4.addBox(-1.5F, -1.5F, 0F, 3, 3, 1);
        l4.setRotationPoint(2.5F, 16.5F, -12F);
        l4.setTextureSize(128, 128);
        l4.mirror = true;
        setRotation(l4, 0F, 0F, 0F);
        l5 = new ModelRenderer(this, 9, 28);
        l5.addBox(-1.5F, -1.5F, 0F, 3, 3, 1);
        l5.setRotationPoint(2.5F, 16.5F, -14F);
        l5.setTextureSize(128, 128);
        l5.mirror = true;
        setRotation(l5, 0F, 0F, 0F);
        r1 = new ModelRenderer(this, 14, 14);
        r1.addBox(-3.9F, -2F, -6F, 3, 3, 3);
        r1.setRotationPoint(0F, 17F, 0F);
        r1.setTextureSize(128, 128);
        r1.mirror = true;
        setRotation(r1, 0F, 0F, 0F);
        r2 = new ModelRenderer(this, 9, 28);
        r2.addBox(-1.5F, -1.5F, 0F, 3, 3, 1);
        r2.setRotationPoint(-2.5F, 16.5F, -8F);
        r2.setTextureSize(128, 128);
        r2.mirror = true;
        setRotation(r2, 0F, 0F, 0F);
        r3 = new ModelRenderer(this, 9, 28);
        r3.addBox(-1.5F, -1.5F, -2F, 3, 3, 1);
        r3.setRotationPoint(-2.5F, 16.5F, -8F);
        r3.setTextureSize(128, 128);
        r3.mirror = true;
        setRotation(r3, 0F, 0F, 0F);
        r4 = new ModelRenderer(this, 9, 28);
        r4.addBox(-1.5F, -1.5F, -4F, 3, 3, 1);
        r4.setRotationPoint(-2.5F, 16.5F, -8F);
        r4.setTextureSize(128, 128);
        r4.mirror = true;
        setRotation(r4, 0F, 0F, 0F);
        r5 = new ModelRenderer(this, 9, 28);
        r5.addBox(-1.5F, -1.5F, -6F, 3, 3, 1);
        r5.setRotationPoint(-2.5F, 16.5F, -8F);
        r5.setTextureSize(128, 128);
        r5.mirror = true;
        setRotation(r5, 0F, 0F, 0F);
        lCap = new ModelRenderer(this, 33, 25);
        lCap.addBox(1F, -2F, -20F, 3, 3, 4);
        lCap.setRotationPoint(0F, 17F, 0F);
        lCap.setTextureSize(128, 128);
        lCap.mirror = true;
        setRotation(lCap, 0F, 0F, 0F);
        rCap = new ModelRenderer(this, 18, 25);
        rCap.addBox(-4F, -2F, -20F, 3, 3, 4);
        rCap.setRotationPoint(0F, 17F, 0F);
        rCap.setTextureSize(128, 128);
        rCap.mirror = true;
        setRotation(rCap, 0F, 0F, 0F);
        Hat = new ModelRenderer(this, 28, 0);
        Hat.addBox(0F, 0F, 0F, 3, 1, 7);
        Hat.setRotationPoint(-1.5F, 12F, -2F);
        Hat.setTextureSize(128, 128);
        Hat.mirror = true;
        setRotation(Hat, 0F, 0F, 0F);
        LowerHat = new ModelRenderer(this, 50, 0);
        LowerHat.addBox(0F, 0F, 0F, 3, 1, 1);
        LowerHat.setRotationPoint(-1.5F, 13F, 4F);
        LowerHat.setTextureSize(128, 128);
        LowerHat.mirror = true;
        setRotation(LowerHat, 0F, 0F, 0F);
        BatteryPack = new ModelRenderer(this, 50, 3);
        BatteryPack.addBox(0F, 0F, 0F, 4, 4, 1);
        BatteryPack.setRotationPoint(-2F, 15F, 5F);
        BatteryPack.setTextureSize(128, 128);
        BatteryPack.mirror = true;
        setRotation(BatteryPack, 0F, 0F, 0F);
        MiddleWire = new ModelRenderer(this, 64, 0);
        MiddleWire.addBox(0F, 0F, 0F, 1, 4, 1);
        MiddleWire.setRotationPoint(-0.5F, 13F, 7F);
        MiddleWire.setTextureSize(128, 128);
        MiddleWire.mirror = true;
        setRotation(MiddleWire, 0F, 0F, 0F);
        BatWire = new ModelRenderer(this, 64, 6);
        BatWire.addBox(0F, 0F, 0F, 1, 1, 1);
        BatWire.setRotationPoint(-0.5F, 16F, 6F);
        BatWire.setTextureSize(128, 128);
        BatWire.mirror = true;
        setRotation(BatWire, 0F, 0F, 0F);
        HatWire = new ModelRenderer(this, 69, 0);
        HatWire.addBox(0F, 0F, 0F, 1, 1, 2);
        HatWire.setRotationPoint(-0.5F, 13F, 5F);
        HatWire.setTextureSize(128, 128);
        HatWire.mirror = true;
        setRotation(HatWire, 0F, 0F, 0F);
        Details = new ModelRenderer(this, 28, 10);
        Details.addBox(0F, 0F, 0F, 3, 1, 1);
        Details.setRotationPoint(-1.5F, 22F, -5.5F);
        Details.setTextureSize(128, 128);
        Details.mirror = true;
        setRotation(Details, 0F, 0F, 0F);
        RightFootStrength = new ModelRenderer(this, 28, 14);
        RightFootStrength.addBox(0F, 0F, 0F, 1, 2, 4);
        RightFootStrength.setRotationPoint(3F, 20F, -2F);
        RightFootStrength.setTextureSize(128, 128);
        RightFootStrength.mirror = true;
        setRotation(RightFootStrength, 0F, 0F, 0F);
        LeftFootStrength = new ModelRenderer(this, 28, 14);
        LeftFootStrength.addBox(0F, 0F, 0F, 1, 2, 4);
        LeftFootStrength.setRotationPoint(-4F, 20F, -2F);
        LeftFootStrength.setTextureSize(128, 128);
        LeftFootStrength.mirror = true;
        setRotation(LeftFootStrength, 0F, 0F, 0F);
        FrontDetail = new ModelRenderer(this, 40, 10);
        FrontDetail.addBox(0F, 0F, 0F, 3, 3, 1);
        FrontDetail.setRotationPoint(-1.5F, 21F, -6.5F);
        FrontDetail.setTextureSize(128, 128);
        FrontDetail.mirror = true;
        setRotation(FrontDetail, 0F, 0F, 0F);
        LeftFootBase = new ModelRenderer(this, 0, 0);
        LeftFootBase.addBox(0F, 0F, 0F, 3, 1, 6);
        LeftFootBase.setRotationPoint(-5.5F, 22F, -3F);
        LeftFootBase.setTextureSize(128, 128);
        LeftFootBase.mirror = true;
        setRotation(LeftFootBase, 0F, 0F, 0F);
        RightFootBase = new ModelRenderer(this, 0, 0);
        RightFootBase.addBox(0F, 0F, 0F, 3, 1, 6);
        RightFootBase.setRotationPoint(2.5F, 22F, -3F);
        RightFootBase.setTextureSize(128, 128);
        RightFootBase.mirror = true;
        setRotation(RightFootBase, 0F, 0F, 0F);
        lEar1 = new ModelRenderer(this, 28, 56);
        lEar1.addBox(2F, -5F, -4F, 1, 5, 1);
        lEar1.setRotationPoint(0F, 17F, 5F);
        lEar1.setTextureSize(128, 128);
        lEar1.mirror = true;
        setRotation(lEar1, -0.6632251F, 0F, 0F);
        lEar2 = new ModelRenderer(this, 28, 56);
        lEar2.addBox(2F, -5F, -4F, 1, 5, 1);
        lEar2.setRotationPoint(0F, 17F, 0F);
        lEar2.setTextureSize(128, 128);
        lEar2.mirror = true;
        setRotation(lEar2, -0.6632251F, 0F, 0F);
        rEar1 = new ModelRenderer(this, 23, 56);
        rEar1.addBox(-3F, -5F, -4F, 1, 5, 1);
        rEar1.setRotationPoint(0F, 17F, 5F);
        rEar1.setTextureSize(128, 128);
        rEar1.mirror = true;
        setRotation(rEar1, -0.6632251F, 0F, 0F);
        rEar2 = new ModelRenderer(this, 23, 56);
        rEar2.addBox(-3F, -5F, -4F, 1, 5, 1);
        rEar2.setRotationPoint(0F, 17F, 0F);
        rEar2.setTextureSize(128, 128);
        rEar2.mirror = true;
        setRotation(rEar2, -0.6632251F, 0F, 0F);
    }

    public void renderYaw(float f5)
    {
        basePlate.render(f5);
        leftMount.render(f5);
        rightMount.render(f5);
        Details.render(f5);
        FrontDetail.render(f5);

        RightFootStrength.render(f5);
        LeftFootStrength.render(f5);
        LeftFootBase.render(f5);
        RightFootBase.render(f5);
    }

    public void renderYawPitch(float f5, float rotation)
    {
        body.render(f5);
        bodyTop.render(f5);
        bodyRight.render(f5);
        bodyLeft.render(f5);
        leftBarrel.render(f5);
        rightBarrel.render(f5);

        l1.render(f5);
        r1.render(f5);

        /** Rotatable laser barrels */
        l2.rotateAngleZ = -rotation;
        l2.render(f5);
        l3.rotateAngleZ = -rotation;
        l3.render(f5);
        l4.rotateAngleZ = -rotation;
        l4.render(f5);
        l5.rotateAngleZ = -rotation;
        l5.render(f5);

        r2.rotateAngleZ = rotation;
        r2.render(f5);
        r3.rotateAngleZ = rotation;
        r3.render(f5);
        r4.rotateAngleZ = rotation;
        r4.render(f5);
        r5.rotateAngleZ = rotation;
        r5.render(f5);

        lCap.render(f5);
        rCap.render(f5);
        Hat.render(f5);
        LowerHat.render(f5);
        BatteryPack.render(f5);
        MiddleWire.render(f5);
        BatWire.render(f5);
        HatWire.render(f5);

        lEar1.render(f5);
        lEar2.render(f5);
        rEar1.render(f5);
        rEar2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
