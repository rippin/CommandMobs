package rippin.bullyscraft.nms.v1_8_R1.Entities;

import net.minecraft.server.v1_8_R1.EntityIronGolem;
import net.minecraft.server.v1_8_R1.World;

public class CustomIronGolem extends EntityIronGolem implements CustomEntity {

    public CustomIronGolem(World world)
    {
        super(world);

        EntityUtils.setNMSEntityNoAI(this);

    }

    @Override
    public void makeSound(String s, float f, float f1) {}

}
