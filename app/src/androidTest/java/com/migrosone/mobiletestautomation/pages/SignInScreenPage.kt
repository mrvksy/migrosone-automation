package com.migrosone.mobiletestautomation.pages

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.printToString
import com.migrosone.mobiletestautomation.constants.InitialScreenConstants
import com.migrosone.mobiletestautomation.constants.SignUpConstants
import com.migrosone.mobiletestautomation.constants.SignInScreenConstants


class SignInScreenPage(private val rule: ComposeTestRule) {
    fun checkEmailFieldDisplayed() {
        rule.onNodeWithText(SignInScreenConstants.EMAIL_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun checkPasswordFieldDisplayed() {
        rule.onNodeWithText(SignInScreenConstants.PASSWORD_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }


    fun checkSignInButtonDisplayed() {
        rule.onNodeWithText(InitialScreenConstants.SIGN_IN_TEXT, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertSignInScreenOpen() {
        rule
            .onAllNodesWithText(SignInScreenConstants.SIGN_IN_TEXT, useUnmergedTree = true)[0]
            .assertIsDisplayed()
    }

    fun fillCredentials(email: String, password: String) {
        rule.onNodeWithText(SignInScreenConstants.EMAIL_LABEL, useUnmergedTree = false)
            .performClick()
            .performTextInput(email)

        rule.onNodeWithText(SignInScreenConstants.PASSWORD_LABEL, useUnmergedTree = false)
            .performClick()
            .performTextInput(password)
    }

    fun setEmailAddressToForm() {
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0]
            .performTextInput(SignInScreenConstants.VALID_EMAIL)
    }

    fun setValidPasswordToTextField() {
        val textFields = rule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)
        textFields[1].performClick().performTextInput(SignInScreenConstants.VALID_PASSWORD)
    }

    fun clickOnSignInButton() {
        val nodes = rule.onAllNodesWithText(SignUpConstants.SIGN_IN_TEXT, useUnmergedTree = true)
        if (nodes.fetchSemanticsNodes().size > 1) {
            try {
                nodes[1].performClick()
            } catch (e: Exception) {
                nodes[1].performTouchInput { click() }
            }
        }

    }

    fun waitProfileImage() {
        rule.waitUntil(timeoutMillis = 10000) {
            rule
                .onAllNodesWithContentDescription(
                    SignInScreenConstants.PROFILE_PICTURE_DESC,
                    useUnmergedTree = true
                )
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
    }

    fun assertProfileImageDisplayed() {
        rule
            .onNodeWithContentDescription(
                SignInScreenConstants.PROFILE_PICTURE_DESC,
                useUnmergedTree = true
            )
            .assertIsDisplayed()
    }

    fun assertWelcomeUserTitleDisplayed() {
        rule.onNodeWithText(
            SignInScreenConstants.WELCOME_TEXT + SignInScreenConstants.VALID_NAME,
            useUnmergedTree = true
        )
            .assertIsDisplayed()
    }

    fun assertEmailAddressFromWelcomePageDisplayed() {
        rule.onNodeWithText(
            SignInScreenConstants.EMAIL_DISPLAY + SignInScreenConstants.VALID_EMAIL, useUnmergedTree = true
        )
            .assertIsDisplayed()
    }

    fun assertAdditionalInfoTextDisplayed() {
        rule.onNodeWithText(SignInScreenConstants.ADDITIONAL_INFO, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertSignOutButtonDisplayed() {
        rule.onNodeWithText(SignInScreenConstants.SIGN_OUT_TEXT, useUnmergedTree = true)
            .assertIsDisplayed()
    }


    fun clickOnSignOutButton() {
        rule
            .onAllNodesWithText(SignInScreenConstants.SIGN_OUT_TEXT, useUnmergedTree = true)[1]
            .performTouchInput { click() }
    }

    // Geçersiz bilgilerle giriş fonksiyonlarını iyileştirme
    fun setIncorrectCredentialsEmailAddressToForm() {
        rule.waitUntil {
            rule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        rule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0]
            .performTextInput(SignInScreenConstants.INCORRECT_CREDENTIALS_EMAIL)
    }

    fun setIncorrectCredentialsPasswordToTextField() {
        rule.waitUntil {
            rule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)
                .fetchSemanticsNodes().size > 1
        }
        rule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)[1]
            .performTextInput(SignInScreenConstants.INCORRECT_CREDENTIALS_PASSWORD)
    }

    fun checkIncorrectUserCredentialsMessage() {
        rule.waitUntil(timeoutMillis = 15000) {
            try {
                // Tam mesaj kontrolü
                rule.onNodeWithText(SignInScreenConstants.INCORRECT_CREDENTIALS_MESSAGE)
                    .assertExists()
                true
            } catch (e: AssertionError) {
                try {
                    // Parçalı kontrol
                    rule.onNodeWithText("User not found", substring = true)
                        .assertExists()
                    true
                } catch (e: Exception) {
                    false
                }
            }
        }
    }

    fun waitForInputFields() {
        rule.waitUntil(timeoutMillis = 5000) {
            rule.onAllNodes(hasSetTextAction(), useUnmergedTree = false)
                .fetchSemanticsNodes().size >= 2
        }
    }

    fun waitEmailFieldForSignInForm() {
        rule.waitUntil(timeoutMillis = 8000) {
            rule.onAllNodesWithText(InitialScreenConstants.EMAIL, useUnmergedTree = true)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
    }


    fun assertCheckSignInFormFields() {
        rule.onNodeWithText(InitialScreenConstants.EMAIL, useUnmergedTree = true)
            .assertIsDisplayed()
        rule.onNodeWithText(InitialScreenConstants.PASSWORD, useUnmergedTree = true)
            .assertIsDisplayed()
        rule
            .onAllNodesWithText(InitialScreenConstants.SIGN_IN_TEXT, useUnmergedTree = true)[0]
            .assertIsDisplayed()
    }




}