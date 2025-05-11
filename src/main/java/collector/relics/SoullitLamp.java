package collector.relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.CardPowerTip;
import collector.CollectorMod;
import collector.cards.Ember;
//import collector.util.EssenceSystem;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.util.TextureLoader;
import theHexaghost.powers.BurnPower;
import utilityClasses.DFL;

import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.makeInHand;

public class SoullitLamp extends CustomRelic {
    public static final String ID = CollectorMod.makeID(SoullitLamp.class.getSimpleName());
    private static final String IMG_PATH = SoullitLamp.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = SoullitLamp.class.getSimpleName() + ".png";

    public SoullitLamp() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.COMMON, LandingSound.MAGICAL);
        tips.add(new CardPowerTip(new Ember()));
    }

    @Override
    public void atBattleStart() {
        flash();
 //       makeInHand(new Ember());
    }

    @Override
    public void onEquip() {
//        EssenceSystem.changeEssence(3);
    }

    @Override
    public void onExhaust(AbstractCard card){
        for (AbstractMonster mon : DFL.activeMonsterList()){
            atb(new ApplyPowerAction(mon, DFL.pl(), new BurnPower(mon, 1), 1));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

