package com.migrosone.mobiletestautomation.test

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.migrosone.mobiletestautomation.MainActivity
import com.migrosone.mobiletestautomation.constants.InitialScreenConstants
import com.migrosone.mobiletestautomation.constants.SignInScreenConstants
import com.migrosone.mobiletestautomation.constants.SignUpConstants
import com.migrosone.mobiletestautomation.pages.InitialScreenPage
import com.migrosone.mobiletestautomation.pages.SignInScreenPage
import com.migrosone.mobiletestautomation.pages.SignUpScreenPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SingInScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private val signUpScreenPage by lazy { SignUpScreenPage(composeTestRule) }

    @Test
    fun userShouldSignUp() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        signUpScreenPage.assertHomePageFieldsVisible()
        Thread.sleep(1000)

        //2. "Sign Up" butonuna tıklandığı doğrulanır.
        signUpScreenPage.clickOnSingUpButton()
        Thread.sleep(1000)

        //3. "Sign Up" ekranında başlık texti doğrulanır.
        signUpScreenPage.assertSignUpTitleExist()

        //4. Gerekli form alanlarının görünüp görünmediği doğrulanır.
        signUpScreenPage.assertSignUpFieldsDisplayed()
        Thread.sleep(500)

        //5. Geçerli Email, Şifre ve İsim bilgisi girilir.
        signUpScreenPage.fillSignUpForm(SignUpConstants.VALID_EMAIL, SignUpConstants.VALID_PASSWORD, SignUpConstants.VALID_NAME)
        Thread.sleep(2000)

        //6. Formun gönderildiği doğrulanır. ("Sign Up" butonuna tıklanır.)
        signUpScreenPage.clickOnSignUpButtonAllNodesWithText()

        //7. "Membership successful!" başarı mesajı için beklenir.
        signUpScreenPage.waitSuccessMembershipMessage()
        signUpScreenPage.assertSuccessMembershipMessage()
        Thread.sleep(1000)

    }

}

