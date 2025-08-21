package collector.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;

public class MysteryWeaving extends AbstractCollectorCard {
    public final static String ID = makeID(MysteryWeaving.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 10, 3, , 

    public MysteryWeaving() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 0;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void applyPowers() {
        int count = DFL.pl().hand.group.size()-1;
        this.baseBlock = count * this.magicNumber;
        if (this.upgraded){
            baseBlock += secondMagic;
        }
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upp() {
        upgradeSecondMagic(3);
        uDesc();
        initializeDescription();
    }
}