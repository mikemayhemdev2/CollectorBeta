package collector.cards.collectibles;

import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import collector.cardmods.CollectedCardMod;
import collector.powers.AddCopyNextTurnPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class GiantHeadCardStageTwo extends AbstractCollectibleCard {
    public final static String ID = makeID(GiantHeadCardStageTwo.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public GiantHeadCardStageTwo() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        MultiCardPreview.add(this, new GiantHeadCardStageThree());
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        GiantHeadCardStageThree tar = new GiantHeadCardStageThree();
        CardModifierManager.addModifier(tar, new CollectedCardMod());
        if (this.upgraded){
            tar.upgrade();
        }
        applyToSelf(new AddCopyNextTurnPower(new GiantHeadCardStageThree()));
    }

    public void upp() {
        uDesc();
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}