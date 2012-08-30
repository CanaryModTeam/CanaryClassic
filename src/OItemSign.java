
public class OItemSign extends OItem {

    public OItemSign(int i) {
        super(i);
        this.bU = 16;
        this.a(OCreativeTabs.c);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if (l == 0) {
            return false;
        } else if (!oworld.f(i, j, k).a()) {
            return false;
        } else {
            // CanaryMod: Store block data clicked
            Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);
            
            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }

            if (!oentityplayer.e(i, j, k)) {
                return false;
            } else if (!OBlock.aD.b(oworld, i, j, k)) {
                return false;
            } else {
                // CanaryMod: Now we can call itemUse :)
                Block blockPlaced = new Block(oworld.world, (l == 1 ? 63 : 68), i, j, k);

                if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
                    return false;
                }
                
                if (l == 1) {
                    int i1 = OMathHelper.c((double) ((oentityplayer.z + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;

                    oworld.d(i, j, k, OBlock.aD.ca, i1);
                } else {
                    oworld.d(i, j, k, OBlock.aI.ca, l);
                }

                --oitemstack.a;
                OTileEntitySign otileentitysign = (OTileEntitySign) oworld.p(i, j, k);

                if (otileentitysign != null) {
                    oentityplayer.a(otileentitysign);
                }

                return true;
            }
        }
    }
}
