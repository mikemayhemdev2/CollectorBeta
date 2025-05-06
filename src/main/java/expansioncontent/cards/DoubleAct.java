package expansioncontent.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import expansioncontent.expansionContentMod;

import static expansioncontent.expansionContentMod.loadJokeCardImage;

public class DoubleAct extends AbstractExpansionCard{
    public final static String ID = makeID("DoubleAct");

    private static final int downfallMagic = 0;

    public DoubleAct() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        //todo skill bg instead of power bg
        this.setBackgroundTexture("expansioncontentResources/images/512/bg_boss_donudeca.png", "expansioncontentResources/images/1024/bg_boss_donudeca.png");
        tags.add(expansionContentMod.STUDY_SHAPES);
        tags.add(expansionContentMod.STUDY);
        baseDownfallMagic = downfallMagic;
        baseMagicNumber = magicNumber = 0;
        loadJokeCardImage(this, "DoubleAct.png");
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnergizedBluePower(AbstractDungeon.player,  p.energy.energyMaster));
        applyToSelf(new DrawCardNextTurnPower(AbstractDungeon.player, p.gameHandSize));
    }

    @Override
    public void upp() {
upgradeBaseCost(2);
    }
}