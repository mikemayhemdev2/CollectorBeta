package collector.cards.collectibles;

import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import collector.cardmods.CollectedCardMod;
import collector.cards.GreaterHurting;
import collector.cards.GreatestHurting;
import collector.powers.AddCopyNextTurnPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToSelf;

public class GiantHeadCardStageOne extends AbstractCollectibleCard {
    public final static String ID = makeID(GiantHeadCardStageOne.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public GiantHeadCardStageOne() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        MultiCardPreview.add(this, new GiantHeadCardStageTwo(), new GiantHeadCardStageThree());
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        GiantHeadCardStageTwo tar = new GiantHeadCardStageTwo();
        CardModifierManager.addModifier(tar, new CollectedCardMod());
        if (this.upgraded){
            tar.upgrade();
        }
        applyToSelf(new AddCopyNextTurnPower(tar));
    }

    public void upp() {
        uDesc();
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }
}