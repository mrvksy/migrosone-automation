package com.migrosone.mobiletestautomation.test

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.migrosone.mobiletestautomation.MainActivity
import com.migrosone.mobiletestautomation.constants.SignUpConstants
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.migrosone.mobiletestautomation.pages.SignUpPage

@RunWith(AndroidJUnit4::class)
class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private val signUpPage by lazy { SignUpPage(composeTestRule) }

    @Test
    fun userShouldSignUpWithEmailPasswordAndNameInformation() {
        // 1. Ana ekran: logo ve butonlar gorulur
        signUpPage.assertHomePageFieldsVisible();

        // 2. Sign Up butonuna tıklanır
        signUpPage.clickOnSingUpButton()

        // 3. Sign Up başlığı kontrolü
        signUpPage.assertSignUpTitleExist()

        // 4. Alanların görünürlüğü
        signUpPage.assertSignUpFieldsDisplayed()

        // 5. Formu doldur
        //signUpPage.fillFormSignUpPage()
        signUpPage.fillSignUpForm(
            SignUpConstants.VALID_EMAIL,
            SignUpConstants.VALID_PASSWORD,
            SignUpConstants.VALID_NAME
        )

        // 6. Sign Up butonuna tıklanır
        signUpPage.clickOnSignUpButtonAllNodesWithText()


        // 7. Başarı mesajı geldiği görülür
        signUpPage.waitSuccessMembershipMessage()
        signUpPage.assertSuccessMembershipMessage()


        // 8. Sign In Now butonuna tiklanır
        signUpPage.clickOnSignInNowButton()
    }

    //Bugs;
    //Bug !!
    @Test
    fun userShouldNotSignUpWithoutRequiredField() {
        // 1. Ana ekran: logo ve butonlar
        signUpPage.assertHomePageFieldsVisible()

        // 2. Sign Up butonuna tıklanır
        signUpPage.clickOnSingUpButton()

        // 3. Sign Up başlığı kontrolü
        signUpPage.assertSignUpTitleExist()

        // 4. Alanların görünürlüğü
        signUpPage.assertSignUpFieldsDisplayed()

        // 5. Sign Up butonuna tıklanır
        signUpPage.clickOnSignUpButtonAllNodesWithText()

        // 6.Membership sucessful! mesajı görülmemeli
        signUpPage.assertNotEqualsSuccessMembershipMessage()
    }

    //Bug !!
    @Test
    fun userShouldNotSignUpWithOnlyUseName() {
        // 1. Ana ekran: logo ve butonlar
        signUpPage.assertHomePageFieldsVisible()

        // 2. Sign Up butonuna tıklanır
        signUpPage.clickOnSingUpButton()

        // 3. Sign Up başlığı kontrolü
        signUpPage.assertSignUpTitleExist()

        // 4. Alanların görünürlüğü
        signUpPage.assertSignUpFieldsDisplayed()

        // 5. Sadece Name girilir
        //signUpPage.setValidName()
        signUpPage.fillSignUpForm("", "", SignUpConstants.VALID_NAME)

        // 5. Sign Up butonuna tıklanır
        signUpPage.clickOnSignUpButtonAllNodesWithText()

        // 6.Membership sucessful! mesajı görülmemeli
        signUpPage.assertNotEqualsSuccessMembershipMessage()

    }

    //Bug !!
    @Test
    fun userShouldNotSignUpWithOnlyUsePassword() {
        // 1. Ana ekran: logo ve butonlar
        signUpPage.assertHomePageFieldsVisible()

        // 2. Sign Up butonuna tıklanır
        signUpPage.clickOnSingUpButton()

        // 3. Sign Up başlığı kontrolü
        signUpPage.assertSignUpTitleExist()

        // 4. Alanların görünürlüğü
        signUpPage.assertSignUpFieldsDisplayed()

        // 5. Sadece Password girilir
        //signUpPage.setValidPassword()
        signUpPage.fillSignUpForm("", SignUpConstants.VALID_PASSWORD, "")

        // 5. Sign Up butonuna tıklanır
        signUpPage.clickOnSignUpButtonAllNodesWithText()

        // 6.Membership sucessful! mesajı görülmemeli
        signUpPage.assertNotEqualsSuccessMembershipMessage()

    }

    //Bug !!
    @Test
    fun userShouldNotSignUpWithOnlyUseEmail() {
        // 1. Ana ekran: logo ve butonlar
        signUpPage.assertHomePageFieldsVisible()

        // 2. Sign Up butonuna tıklanır
        signUpPage.clickOnSingUpButton()

        // 3. Sign Up başlığı kontrolü
        signUpPage.assertSignUpTitleExist()

        // 4. Alanların görünürlüğü
        signUpPage.assertSignUpFieldsDisplayed()

        // 5. Sadece Email girilir
        //signUpPage.setValidEmail()
        signUpPage.fillSignUpForm(SignUpConstants.VALID_EMAIL, "", "")

        // 6. Sign Up butonuna tıklanır
        signUpPage.clickOnSignUpButtonAllNodesWithText()

        // 7.Membership sucessful! mesajı görülmemeli
        signUpPage.assertNotEqualsSuccessMembershipMessage()
    }

    //Bug !!
    @Test
    fun userShouldNotSignUpWithNotValidEmail() {
        // 1. Ana ekran: logo ve butonlar
        signUpPage.assertHomePageFieldsVisible()

        // 2. Sign Up butonuna tıklanır
        signUpPage.clickOnSingUpButton()

        // 3. Sign Up başlığı kontrolü
        signUpPage.assertSignUpTitleExist()

        // 4. Alanların görünürlüğü
        signUpPage.assertSignUpFieldsDisplayed()

        // 5. Formu doldur
        // Invalid Email
        //signUpPage.setInvalidEmail()
        // Password
        //signUpPage.setValidPassword()
        // Name
        //signUpPage.setValidName()

        signUpPage.fillSignUpForm(
            SignUpConstants.INVALID_EMAIL,
            SignUpConstants.VALID_PASSWORD,
            SignUpConstants.VALID_NAME
        )

        // 6. Sign Up butonuna tıklanır
        signUpPage.clickOnSignUpButtonAllNodesWithText()

        // 7. Membership successful! mesajının gelmediği görülür
        signUpPage.assertNotEqualsSuccessMembershipMessage()
    }

}






