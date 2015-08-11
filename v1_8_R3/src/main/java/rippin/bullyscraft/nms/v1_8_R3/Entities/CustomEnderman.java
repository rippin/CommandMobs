package rippin.bullyscraft.nms.v1_8_R3.Entities;


import net.minecraft.server.v1_8_R3.EntityEnderman;
import net.minecraft.server.v1_8_R3.World;

public class CustomEnderman extends EntityEnderman implements CustomEntity {

    public CustomEnderman(World world)
    {
        super(world);

        EntityUtils.setNMSEntityNoAI(this);

    }

    @Override
    public void makeSound(String s, float f, float f1) {}
    @Override
    public void move(double d0, double d1, double d2){}


}
