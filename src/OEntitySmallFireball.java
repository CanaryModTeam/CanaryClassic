public class OEntitySmallFireball extends OEntityFireball {

    public OEntitySmallFireball(OWorld oworld) {
        super(oworld);
        this.a(0.3125F, 0.3125F);
    }

    public OEntitySmallFireball(OWorld oworld, OEntityLivingBase oentitylivingbase, double d0, double d1, double d2) {
        super(oworld, oentitylivingbase, d0, d1, d2);
        this.a(0.3125F, 0.3125F);
    }

    public OEntitySmallFireball(OWorld oworld, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(oworld, d0, d1, d2, d3, d4, d5);
        this.a(0.3125F, 0.3125F);
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, this.getEntity(), omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity()) && !this.q.I) {
            if (omovingobjectposition.g != null) {
                if (!omovingobjectposition.g.F() && omovingobjectposition.g.a(ODamageSource.a((OEntityFireball) this, this.a), 5.0F)) {
                    omovingobjectposition.g.d(5);
                }
            } else {
                int i = omovingobjectposition.b;
                int j = omovingobjectposition.c;
                int k = omovingobjectposition.d;

                switch (omovingobjectposition.e) {
                    case 0:
                        --j;
                        break;

                    case 1:
                        ++j;
                        break;

                    case 2:
                        --k;
                        break;

                    case 3:
                        ++k;
                        break;

                    case 4:
                        --i;
                        break;

                    case 5:
                        ++i;
                }

                if (this.q.c(i, j, k)) {
                    // CanaryMod start: IGNITE hook
                    Block b = new Block(this.q.world, 0, i, j, k);
                    b.setStatus(7); // Fireball explosion
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, b, null)) {
                        this.q.c(i, j, k, OBlock.aw.cF);
                    } // CanaryMod end
                }
            }

            this.x();
        }
    }

    public boolean L() {
        return false;
    }

    public boolean a(ODamageSource odamagesource, float f) {
        return false;
    }
}
