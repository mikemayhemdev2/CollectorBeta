package theHexaghost.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import downfall.util.TextureLoader;
import theHexaghost.HexaMod;
import theHexaghost.relics.CandleOfCauterizing;
import theHexaghost.vfx.ExplosionSmallEffectGreen;

import static utilityClasses.Wiz.atb;

public class BurnPower extends TwoAmountPower implements CloneablePowerInterface, HealthBarRenderPower {

    public static final String POWER_ID = HexaMod.makeID("BurnPower");

    private static final Texture tex84 = TextureLoader.getTexture(HexaMod.getModID() + "Resources/images/powers/Burning84.png");
    private static final Texture tex32 = TextureLoader.getTexture(HexaMod.getModID() + "Resources/images/powers/Burning32.png");
    public static Color myColor = new Color(0.529F, 0.922F, 0, 1);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BurnPower(final AbstractCreature owner, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        amount2 = 3;
//        if (AbstractDungeon.player.hasRelic(IceCube.ID)) amount2 = 4;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public int getHealthBarAmount() {
        if (amount2 == 1)
            return amount;
        return 0;
    }

    @Override
    public Color getColor() {
        return myColor.cpy();
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && amount2 == 1) {// 65 66
            explode(false);
        } else {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    amount2--;
                    updateDescription();
                }
            });
        }
    }


    public void explode(boolean fast_explode) {
        explode(fast_explode, false);
    }

    public void explode(boolean fast_explode, boolean leaveone) {
        this.flashWithoutSound();
        int explodeamount;
        if (fast_explode) {// for phantom fireball so that it detonates first before the searing flame applies soulburn
            if (leaveone) {
                explodeamount = this.amount - 1;
                this.amount = 1;
            } else {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                explodeamount = this.amount;
            }
            this.addToTop(new VFXAction(new ExplosionSmallEffectGreen(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
        } else {
            this.addToBot(new VFXAction(new ExplosionSmallEffectGreen(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
            if (leaveone) {
                explodeamount = this.amount - 1;
                this.amount = 1;
            } else {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                explodeamount = this.amount;
            }

        }

        if (owner.hasPower(LivingBombPower.POWER_ID)) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDeadOrEscaped()) {
                    this.addToBot(new LoseHPAction(m, owner, explodeamount, AbstractGameAction.AttackEffect.FIRE));
                }
            }
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, LivingBombPower.POWER_ID));

        } else {
            this.addToBot(new LoseHPAction(owner, owner, explodeamount, AbstractGameAction.AttackEffect.FIRE));
        }

        if (AbstractDungeon.player.hasPower(CrispyPower_new.POWER_ID)) {
            {
                atb(new ApplyPowerAction(owner, AbstractDungeon.player, new BurnPower(owner, AbstractDungeon.player.getPower(CrispyPower_new.POWER_ID).amount), AbstractDungeon.player.getPower(CrispyPower_new.POWER_ID).amount));
                if (AbstractDungeon.player.hasRelic(CandleOfCauterizing.ID)) {
                    AbstractRelic r = AbstractDungeon.player.getRelic(CandleOfCauterizing.ID);
                    r.flash();
                }
            }
        }

    }

    @Override
    public void updateDescription() {
        if (amount2 == 1)
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
    }

    @Override
    public AbstractPower makeCopy() {
        return new BurnPower(owner, amount);
    }
}