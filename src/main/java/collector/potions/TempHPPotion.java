package collector.potions;


import basemod.abstracts.CustomPotion;
import collector.actions.kaboomActionTuah;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class TempHPPotion extends CustomPotion {
    public static final String POTION_ID = makeID(TempHPPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public TempHPPotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.BOTTLE, PotionColor.WHITE);
        this.isThrown = true;
        this.targetRequired = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        if (AbstractDungeon.player != null && potency > 1) {
            this.description = potionStrings.DESCRIPTIONS[1] + potency + potionStrings.DESCRIPTIONS[2];
        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        atb(new kaboomActionTuah(this.potency));
    }

    public CustomPotion makeCopy() {
        return new TempHPPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}
