package entities;
import enums.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.beans.property.SimpleStringProperty; 
import javafx.scene.Node;  
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
// Add these imports at the top of your file
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import utils.CourseUtils;
import java.util.Map;
import javafx.scene.shape.Rectangle;
import javafx.animation.ScaleTransition;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.text.FontPosture;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;

public class Main extends Application {
    private Admin admin;
    private ListView<String> userListView;
    private TableView<Course> ongoingCoursesTable;
    private TableView<Course> historyCoursesTable;
    
    @Override
    public void start(Stage primaryStage) {
        admin = new Admin();

        // Initialize predefined users
        entities.UserInitializer.initializeUsers(admin);

        // Create modern CSS style
        String css = createCustomCss();
        
        // Create main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("main-container");

        // Create modern header with animation
        StackPane headerPane = createAnimatedHeader();
        
        // Create TabPane for different screens with modern styling
        TabPane mainTabPane = new TabPane();
        mainTabPane.getStyleClass().add("main-tab-pane");

        // Create tabs for different sections with icons
        Tab usersTab = createUsersTab();
        usersTab.setGraphic(createIcon("👥"));
        
        Tab coursesTab = createCoursesTab();
        coursesTab.setGraphic(createIcon("🚗"));
        
        Tab statsTab = createStatsTab();
        statsTab.setGraphic(createIcon("📊"));
        
        Tab itinerariesTab = createItinerariesTab();
        itinerariesTab.setGraphic(createIcon("🗺️"));

        mainTabPane.getTabs().addAll(usersTab, coursesTab, statsTab, itinerariesTab);

        // Add components to main layout
        mainLayout.setTop(headerPane);
        mainLayout.setCenter(mainTabPane);

        // Create modern footer
        HBox footer = createFooter();
        mainLayout.setBottom(footer);

        // Create scene with custom styling
        Scene scene = new Scene(mainLayout, 1200, 800);
        try {
            String encodedCss = java.net.URLEncoder.encode(css, java.nio.charset.StandardCharsets.UTF_8.toString())
                .replace("+", "%20");
            scene.getStylesheets().add("data:text/css," + encodedCss);
        } catch (Exception ex) {
            System.err.println("Error loading CSS: " + ex.getMessage());
            // Fallback to simple styling
            mainLayout.setStyle("-fx-background-color: white;");
        }
        
        primaryStage.setTitle("USTHB Carpooling - Système de Gestion");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        
        // Add nice icon if available
        try {
            primaryStage.getIcons().add(new Image("file:entities/usthb_logo.png"));
        } catch (Exception ex) {
            // No icon available, continue without it
        }
        
        // Show the stage with a fade-in effect
        primaryStage.show();
        FadeTransition ft = new FadeTransition(Duration.millis(1000), mainLayout);
        ft.setFromValue(0.3);
        ft.setToValue(1.0);
        ft.play();
    }
    
    private StackPane createAnimatedHeader() {
        // Main container
        StackPane headerStack = new StackPane();
        headerStack.getStyleClass().add("header");
        headerStack.setPrefHeight(120);
        
        // Background with gradient
        Rectangle bg = new Rectangle();
        bg.widthProperty().bind(headerStack.widthProperty());
        bg.heightProperty().bind(headerStack.heightProperty());
        bg.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.valueOf("#1a237e")),
                new Stop(0.5, Color.valueOf("#283593")),
                new Stop(1, Color.valueOf("#3949ab"))));
        
        // Content container
        HBox content = new HBox(20);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(10));
        
        // Logo/App Icon
        StackPane logoContainer = new StackPane();
        logoContainer.setPrefSize(80, 80);
        logoContainer.getStyleClass().add("logo-container");
        
        Text logoText = new Text("UC");
        logoText.getStyleClass().add("logo-text");
        
        try {
            ImageView logoView = new ImageView(new Image("file:entities/usthb_logo.png"));
            logoView.setFitHeight(60);
            logoView.setPreserveRatio(true);
            logoContainer.getChildren().add(logoView);
        } catch (Exception ex) {
            // If logo not found, use text instead
            logoContainer.getChildren().add(logoText);
        }
        
        // Title and subtitle
        VBox titleBox = new VBox(5);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        
        Label titleLabel = new Label("USTHB CARPOOLING");
        titleLabel.getStyleClass().add("app-title");
        
        Label subtitleLabel = new Label("Système Intelligent de Gestion du Covoiturage Universitaire");
        subtitleLabel.getStyleClass().add("app-subtitle");
        
        titleBox.getChildren().addAll(titleLabel, subtitleLabel);
        
        // Stats summary
        VBox statsBox = new VBox(5);
        statsBox.setAlignment(Pos.CENTER_RIGHT);
        statsBox.getStyleClass().add("header-stats");
        
        int totalUsers = admin.getNombreTotalUser();
        int totalCourses = admin.getHistoriqueCourses().size() + admin.getCoursesEnCours().size();
        
        Label usersLabel = new Label("👤 " + totalUsers + " utilisateurs");
        Label coursesLabel = new Label("🚗 " + totalCourses + " courses");
        
        statsBox.getChildren().addAll(usersLabel, coursesLabel);
        
        // Add all elements
        content.getChildren().addAll(logoContainer, titleBox);
        HBox.setHgrow(titleBox, Priority.ALWAYS);
        content.getChildren().add(statsBox);
        
        headerStack.getChildren().addAll(bg, content);
        
        // Add subtle animation on hover
        headerStack.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), logoContainer);
            st.setToX(1.05);
            st.setToY(1.05);
            st.play();
        });
        
        headerStack.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), logoContainer);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
        
        return headerStack;
    }

    private HBox createFooter() {
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.setPadding(new Insets(10, 20, 10, 20));
        footer.setAlignment(Pos.CENTER_RIGHT);
        
        Label statusLabel = new Label("© 2023 USTHB Carpooling • Tous droits réservés");
        statusLabel.getStyleClass().add("footer-text");
        
        footer.getChildren().add(statusLabel);
        return footer;
    }

    private Label createIcon(String emoji) {
        Label icon = new Label(emoji);
        icon.getStyleClass().add("tab-icon");
        return icon;
    }

    private VBox createStatBox(String title, String value) {
        // Create a modern stat box with shadows and hover effect
        VBox statBox = new VBox(5);
        statBox.getStyleClass().add("stat-box");
        statBox.setPadding(new Insets(15));
        statBox.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("stat-title");
        
        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("stat-value");
        
        statBox.getChildren().addAll(valueLabel, titleLabel);
        
        // Add hover effect
        statBox.setOnMouseEntered(e -> {
            statBox.getStyleClass().add("stat-box-hover");
        });
        
        statBox.setOnMouseExited(e -> {
            statBox.getStyleClass().remove("stat-box-hover");
        });
        
        return statBox;
    }

    // Create a button with icon and tooltip
    private Button createIconButton(String text, String iconText, String tooltipText) {
        Button button = new Button(text);
        button.getStyleClass().add("icon-button");
        
        if (iconText != null && !iconText.isEmpty()) {
            Label icon = new Label(iconText);
            icon.getStyleClass().add("button-icon");
            button.setGraphic(icon);
        }
        
        if (tooltipText != null && !tooltipText.isEmpty()) {
            Tooltip tooltip = new Tooltip(tooltipText);
            tooltip.setShowDelay(Duration.millis(300));
            Tooltip.install(button, tooltip);
        }
        
        return button;
    }

    // Create custom CSS for the application
    private String createCustomCss() {
        return """
            .main-container {
                -fx-background-color: #f5f5f5;
            }
            
            .header {
                -fx-background-color: linear-gradient(to right, #3949ab, #5c6bc0);
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);
            }
            
            .logo-container {
                -fx-background-color: white;
                -fx-background-radius: 50%;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 2);
                -fx-padding: 10;
            }
            
            .logo-text {
                -fx-font-size: 28px;
                -fx-font-weight: bold;
                -fx-fill: #3949ab;
            }
            
            .app-title {
                -fx-font-size: 24px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
                -fx-font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
            
            .app-subtitle {
                -fx-font-size: 14px;
                -fx-text-fill: rgba(255, 255, 255, 0.8);
                -fx-font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
            
            .header-stats {
                -fx-text-fill: white;
                -fx-font-size: 13px;
                -fx-background-color: rgba(255, 255, 255, 0.1);
                -fx-background-radius: 5;
                -fx-padding: 8 15;
            }
            
            .header-stats .label {
                -fx-text-fill: white;
            }
            
            .main-tab-pane {
                -fx-background-color: transparent;
                -fx-tab-min-width: 120px;
            }
            
            .main-tab-pane .tab {
                -fx-background-color: rgba(255, 255, 255, 0.8);
                -fx-background-radius: 5 5 0 0;
                -fx-padding: 8 15;
            }
            
            .main-tab-pane .tab:selected {
                -fx-background-color: white;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 4, 0, 0, 1);
            }
            
            .main-tab-pane .tab-header-area {
                -fx-padding: 5 0 0 5;
            }
            
            .main-tab-pane .tab-header-background {
                -fx-background-color: transparent;
            }
            
            .tab-icon {
                -fx-font-size: 16px;
                -fx-padding: 0 5 0 0;
            }
            
            .tab-pane > .tab-header-area > .tab-header-background {
                -fx-background-color: rgba(255, 255, 255, 0.2);
                -fx-background-radius: 5 5 0 0;
            }
            
            .stat-box {
                -fx-background-color: white;
                -fx-background-radius: 8;
                -fx-border-radius: 8;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);
                -fx-min-width: 150px;
                -fx-transition: -fx-background-color 0.3s;
            }
            
            .stat-box-hover {
                -fx-background-color: #f0f8ff;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);
            }
            
            .stat-title {
                -fx-font-size: 14px;
                -fx-text-fill: #666;
                -fx-font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
            
            .stat-value {
                -fx-font-size: 28px;
                -fx-font-weight: bold;
                -fx-text-fill: #3949ab;
                -fx-font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }
            
            .titled-pane {
                -fx-text-fill: #333;
                -fx-font-size: 14px;
            }
            
            .titled-pane > .title {
                -fx-background-color: linear-gradient(to bottom, #f9f9f9, #e9e9e9);
                -fx-background-radius: 5;
                -fx-font-weight: bold;
                -fx-padding: 8 12;
            }
            
            .titled-pane > .content {
                -fx-background-color: white;
                -fx-background-radius: 0 0 5 5;
                -fx-border-radius: 0 0 5 5;
                -fx-border-color: #ddd;
                -fx-border-width: 1;
            }
            
            .button {
                -fx-background-color: #3949ab;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 4;
                -fx-padding: 8 15;
                -fx-cursor: hand;
            }
            
            .button:hover {
                -fx-background-color: #5c6bc0;
            }
            
            .button:pressed {
                -fx-background-color: #303f9f;
            }
            
            .icon-button {
                -fx-background-color: #3949ab;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 4;
                -fx-alignment: center;
                -fx-content-display: left;
                -fx-padding: 8 15;
                -fx-cursor: hand;
            }
            
            .button-icon {
                -fx-font-size: 16px;
                -fx-padding: 0 8 0 0;
            }
            
            .text-field, .combo-box {
                -fx-background-color: white;
                -fx-background-radius: 4;
                -fx-border-color: #ccc;
                -fx-border-radius: 4;
                -fx-padding: 8;
            }
            
            .text-field:focused, .combo-box:focused {
                -fx-border-color: #3949ab;
                -fx-effect: dropshadow(gaussian, rgba(57, 73, 171, 0.4), 4, 0, 0, 0);
            }
            
            .list-view, .table-view {
                -fx-background-color: white;
                -fx-background-radius: 4;
                -fx-border-color: #ddd;
                -fx-border-radius: 4;
            }
            
            .list-view .list-cell, .table-view .table-cell {
                -fx-padding: 8;
            }
            
            .list-view .list-cell:odd, .table-view .table-row-cell:odd {
                -fx-background-color: #f9f9f9;
            }
            
            .list-view .list-cell:selected, .table-view .table-row-cell:selected {
                -fx-background-color: #e8eaf6;
                -fx-text-fill: #3949ab;
            }
            
            .scroll-pane {
                -fx-background-color: transparent;
            }
            
            .scroll-pane > .viewport {
                -fx-background-color: transparent;
            }
            
            .scroll-bar:vertical, .scroll-bar:horizontal {
                -fx-background-color: transparent;
            }
            
            .scroll-bar:vertical .thumb, .scroll-bar:horizontal .thumb {
                -fx-background-color: rgba(0,0,0,0.2);
                -fx-background-radius: 3;
            }
            
            .footer {
                -fx-background-color: linear-gradient(to right, #323232, #424242);
                -fx-padding: 10;
            }
            
            .footer-text {
                -fx-text-fill: white;
                -fx-font-size: 12px;
            }
            
            .separator {
                -fx-opacity: 0.5;
            }
            
            .spinner {
                -fx-background-color: white;
                -fx-background-radius: 4;
                -fx-border-color: #ccc;
                -fx-border-radius: 4;
            }
            
            .spinner .increment-arrow-button, .spinner .decrement-arrow-button {
                -fx-background-color: #f0f0f0;
            }
            
            .chart {
                -fx-background-color: transparent;
            }
            
            /* Rating system styles */
            .rating-spinner {
                -fx-pref-width: 100px;
            }
            
            .star-filled {
                -fx-text-fill: #ffd700;
                -fx-font-size: 16px;
            }
            
            .star-empty {
                -fx-text-fill: #cccccc;
                -fx-font-size: 16px;
            }
            
            .review-box {
                -fx-background-color: #f9f9f9;
                -fx-background-radius: 5;
                -fx-padding: 10;
                -fx-border-color: #eeeeee;
                -fx-border-radius: 5;
            }
            
            .review-title {
                -fx-font-weight: bold;
            }
            
            .no-review-label {
                -fx-text-fill: #999999;
                -fx-font-style: italic;
            }
            
            /* User details styles */
            .info-value {
                -fx-font-weight: bold;
            }
            
            .user-name {
                -fx-font-size: 16px;
                -fx-font-weight: bold;
            }
            
            .reputation-value {
                -fx-font-weight: bold;
                -fx-text-fill: #3949ab;
            }
            
            .user-type-badge {
                -fx-background-color: #e0e0e0;
                -fx-background-radius: 3;
                -fx-padding: 2 8;
                -fx-font-size: 11px;
            }
            
            .user-type-label {
                -fx-text-fill: #3949ab;
                -fx-font-weight: bold;
            }
            
            .status-badge {
                -fx-background-radius: 3;
                -fx-padding: 3 10;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-color: #7986cb;
            }
            
            .status-ongoing {
                -fx-background-color: #4caf50;
            }
            
            .status-completed {
                -fx-background-color: #9e9e9e;
            }
            
            .user-card {
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);
            }
            
            .course-details-container {
                -fx-background-color: white;
            }
            
            .detail-label {
                -fx-font-weight: bold;
            }
            
            .section-header {
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-fill: #3949ab;
            }
            
            .demand-dialog {
                -fx-background-color: white;
            }
        """;
    }
    
    // TAB 1: Users Management
    private Tab createUsersTab() {
        Tab tab = new Tab("Gestion des Utilisateurs");
        tab.setClosable(false);
        
        // Create a border pane for this tab's layout
        BorderPane userLayout = new BorderPane();
        
        // Create tab pane for different user types
        TabPane userTypeTabPane = new TabPane();
        userTypeTabPane.getStyleClass().add("nested-tab-pane");
        
        // Create tabs for different user types
        Tab etudiantTab = createEtudiantTab();
        Tab enseignantTab = createEnseignantTab();
        Tab atsTab = createATSTab();
        
        userTypeTabPane.getTabs().addAll(etudiantTab, enseignantTab, atsTab);
        
        // Create right panel for user list and actions
        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setPrefWidth(300);
        rightPanel.getStyleClass().add("sidebar-panel");
        
        Label usersListLabel = new Label("Liste des utilisateurs");
        usersListLabel.getStyleClass().add("section-header");
        
        // Create list view for users
        userListView = new ListView<>();
        userListView.setPrefHeight(300);
        updateUserList(); // Populate the list
        
        // Filter by user type
        Label filterLabel = new Label("Filtrer par type:");
        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Tous", "Étudiants", "Enseignants", "ATS", "Chauffeurs", "Passagers");
        filterComboBox.setValue("Tous");
        
        filterComboBox.setOnAction(e -> {
            userListView.getItems().clear();
            String filter = filterComboBox.getValue();
            
            for (User user : admin.getUtilisateurs()) {
                boolean shouldShow = false;
                
                switch (filter) {
                    case "Tous":
                        shouldShow = true;
                        break;
                    case "Étudiants":
                        shouldShow = user instanceof Etudiant;
                        break;
                    case "Enseignants":
                        shouldShow = user instanceof Enseignant;
                        break;
                    case "ATS":
                        shouldShow = user instanceof ATS;
                        break;
                    case "Chauffeurs":
                        shouldShow = user.getProfile().getStatut() == StatusUser.CHAUFFEUR;
                        break;
                    case "Passagers":
                        shouldShow = user.getProfile().getStatut() == StatusUser.PASSAGER;
                        break;
                }
                
                if (shouldShow) {
                    String statusType = user.getProfile().getStatut() == StatusUser.CHAUFFEUR ? 
                        "Chauffeur" : "Passager";
                    
                    String userType = "Inconnu";
                    if (user instanceof Etudiant) {
                        userType = "Etudiant";
                    } else if (user instanceof Enseignant) {
                        userType = "Enseignant";
                    } else if (user instanceof ATS) {
                        userType = "ATS";
                    }
                    
                    userListView.getItems().add(String.format("%s %s (%s) - %s - %s", 
                        user.getNom(), 
                        user.getPrenom(), 
                        user.getMatricule(), 
                        userType,
                        statusType
                    ));
                }
            }
        });
        
        // Buttons for user actions
        Button showDetailsButton = createIconButton("Voir détails", "🔍", "Voir les détails de l'utilisateur sélectionné");
        showDetailsButton.setMaxWidth(Double.MAX_VALUE);
        
        Button demandeCourseButton = createIconButton("Demande de Course", "🚗", "Créer une nouvelle demande de course");
        demandeCourseButton.setMaxWidth(Double.MAX_VALUE);
        
        Button changeStatusButton = createIconButton("Changer statut", "🔄", "Changer le statut de l'utilisateur sélectionné");
        changeStatusButton.setMaxWidth(Double.MAX_VALUE);
        
        // Event handlers for buttons
        showDetailsButton.setOnAction(e -> {
            String selected = userListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Erreur", "Veuillez sélectionner un utilisateur.");
                return;
            }
            
            User user = findUserByListItem(selected);
            if (user == null) {
                showAlert("Erreur", "Utilisateur non trouvé.");
                return;
            }
            
            showUserDetailsDialog(user);
        });
        
        demandeCourseButton.setOnAction(e -> showDemandeDeCourseDialog());
        
        changeStatusButton.setOnAction(e -> {
            String selected = userListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Erreur", "Veuillez sélectionner un utilisateur.");
                return;
            }
            
            User user = findUserByListItem(selected);
            if (user == null) {
                showAlert("Erreur", "Utilisateur non trouvé.");
                return;
            }
            
            showChangeStatusDialog(user);
        });
        
        rightPanel.getChildren().addAll(
            usersListLabel, 
            userListView, 
            new Separator(), 
            filterLabel, 
            filterComboBox, 
            showDetailsButton,
            new Separator(), 
            demandeCourseButton, 
            changeStatusButton
        );
        
        // Add components to user layout
        userLayout.setCenter(userTypeTabPane);
        userLayout.setRight(rightPanel);
        
        tab.setContent(userLayout);
        return tab;
    }
    
    // TAB 2: Courses Management
    private Tab createCoursesTab() {
        Tab tab = new Tab("Gestion des Courses");
        tab.setClosable(false);
        
        TabPane coursesTabPane = new TabPane();
        
        // Tab for ongoing courses
        Tab ongoingTab = new Tab("Courses en cours");
        ongoingTab.setClosable(false);
        
        VBox ongoingContent = new VBox(10);
        ongoingContent.setPadding(new Insets(10));
        
        Label ongoingLabel = new Label("Courses en cours");
        ongoingLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        // Create table for ongoing courses
        ongoingCoursesTable = new TableView<>();
        setupCoursesTable(ongoingCoursesTable);
        
        // Buttons for ongoing courses actions
        HBox ongoingActionsBox = new HBox(10);
        Button endCourseButton = new Button("Terminer la course");
        Button refreshOngoingButton = new Button("Rafraîchir");
        
        ongoingActionsBox.getChildren().addAll(endCourseButton, refreshOngoingButton);
        
        // Action to end a course
        endCourseButton.setOnAction(e -> endSelectedCourse());
        
        // Action to refresh the list
        refreshOngoingButton.setOnAction(e -> refreshCoursesTables());
        
        ongoingContent.getChildren().addAll(ongoingLabel, ongoingCoursesTable, ongoingActionsBox);
        ongoingTab.setContent(ongoingContent);
        
        // Tab for course history
        Tab historyTab = new Tab("Historique des courses");
        historyTab.setClosable(false);
        
        VBox historyContent = new VBox(10);
        historyContent.setPadding(new Insets(10));
        
        Label historyLabel = new Label("Historique des courses");
        historyLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        // Create table for history courses
        historyCoursesTable = new TableView<>();
        setupCoursesTable(historyCoursesTable);
        
        // Buttons for history courses actions
        HBox historyActionsBox = new HBox(10);
        Button viewDetailsButton = new Button("Voir détails");
        Button refreshHistoryButton = new Button("Rafraîchir");
        
        historyActionsBox.getChildren().addAll(viewDetailsButton, refreshHistoryButton);
        
        // Action to view course details
        viewDetailsButton.setOnAction(e -> viewCourseDetails(historyCoursesTable));
        
        // Action to refresh the list
        refreshHistoryButton.setOnAction(e -> refreshCoursesTables());
        
        historyContent.getChildren().addAll(historyLabel, historyCoursesTable, historyActionsBox);
        historyTab.setContent(historyContent);
        
        // Add tabs to courses tab pane
        coursesTabPane.getTabs().addAll(ongoingTab, historyTab);
        
        tab.setContent(coursesTabPane);
        
        // Initial load of courses data
        refreshCoursesTables();
        
        return tab;
    }
    
    // TAB 3: Statistics and Admin Functions
    private Tab createStatsTab() {
        Tab tab = new Tab("Statistiques et Administration");
        tab.setClosable(false);
        
        ScrollPane scrollPane = new ScrollPane();
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        
        // Header with title
        Label titleLabel = new Label("Tableau de bord et statistiques");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        // Statistics cards section
        TitledPane statsPane = createStatsPane();
        
        // User ranking section
        TitledPane rankingPane = createRankingPane();
        
        // User management section
        TitledPane userManagementPane = createUserManagementPane();
        
        // Add all to content
        content.getChildren().addAll(titleLabel, statsPane, rankingPane, userManagementPane);
        
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        tab.setContent(scrollPane);
        
        return tab;
    }
    
    // TAB 4: Itineraries Management
    private Tab createItinerariesTab() {
        Tab tab = new Tab("Gestion des Itinéraires");
        tab.setClosable(false);
        
        BorderPane itinerariesLayout = new BorderPane();
        
        // Left panel for filters
        VBox leftPanel = new VBox(10);
        leftPanel.setPadding(new Insets(10));
        leftPanel.setPrefWidth(200);
        
        Label filterLabel = new Label("Filtres");
        filterLabel.setStyle("-fx-font-weight: bold;");
        
        // User type filter
        Label typeLabel = new Label("Type d'utilisateur:");
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().setAll("Tous", "Étudiants", "Enseignants", "ATS");
        typeComboBox.setValue("Tous");
        
        // Status filter
        Label statusLabel = new Label("Statut:");
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Tous", "Chauffeur", "Passager");
        statusComboBox.setValue("Tous");
        
        // Apply filters button
        Button applyFiltersButton = new Button("Appliquer les filtres");
        applyFiltersButton.setMaxWidth(Double.MAX_VALUE);
        
        leftPanel.getChildren().addAll(filterLabel, typeLabel, typeComboBox, 
                                     statusLabel, statusComboBox, applyFiltersButton);
        
        // Center panel for user list and itinerary details
        SplitPane centerPanel = new SplitPane();
        
        // User list
        VBox userListBox = new VBox(10);
        userListBox.setPadding(new Insets(10));
        
        Label userListLabel = new Label("Liste des utilisateurs");
        userListLabel.setStyle("-fx-font-weight: bold;");
        
        ListView<User> userListView = new ListView<>();
        userListView.setCellFactory(lv -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getNom() + " " + user.getPrenom() + 
                           " (" + user.getClass().getSimpleName() + ")");
                }
            }
        });
        
        userListBox.getChildren().addAll(userListLabel, userListView);
        
        // Itinerary details
        VBox itineraryBox = new VBox(10);
        itineraryBox.setPadding(new Insets(10));
        
        Label itineraryLabel = new Label("Détails de l'itinéraire");
        itineraryLabel.setStyle("-fx-font-weight: bold;");
        
        TextArea itineraryDetails = new TextArea();
        itineraryDetails.setEditable(false);
        itineraryDetails.setWrapText(true);
        
        itineraryBox.getChildren().addAll(itineraryLabel, itineraryDetails);
        
        centerPanel.getItems().addAll(userListBox, itineraryBox);
        centerPanel.setDividerPositions(0.3);
        
        // Add components to layout
        itinerariesLayout.setLeft(leftPanel);
        itinerariesLayout.setCenter(centerPanel);
        
        // Event handlers
        applyFiltersButton.setOnAction(e -> {
            String selectedType = typeComboBox.getValue();
            String selectedStatus = statusComboBox.getValue();
            
            List<User> filteredUsers = admin.getUtilisateurs().stream()
                .filter(user -> {
                    boolean typeMatch = selectedType.equals("Tous") ||
                        (selectedType.equals("Étudiants") && user instanceof Etudiant) ||
                        (selectedType.equals("Enseignants") && user instanceof Enseignant) ||
                        (selectedType.equals("ATS") && user instanceof ATS);
                    
                    boolean statusMatch = selectedStatus.equals("Tous") ||
                        (selectedStatus.equals("Chauffeur") && user.getProfile().getStatut() == StatusUser.CHAUFFEUR) ||
                        (selectedStatus.equals("Passager") && user.getProfile().getStatut() == StatusUser.PASSAGER);
                    
                    return typeMatch && statusMatch;
                })
                .collect(Collectors.toList());
            
            userListView.getItems().setAll(filteredUsers);
        });
        
        userListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Profile profile = newVal.getProfile();
                Disponibilite disponibilite = profile.getDisponibilite();
                
                StringBuilder details = new StringBuilder();
                details.append("Type: ").append(profile.getStatut()).append("\n");
                details.append("Type de course: ").append(profile.getTypeCourse()).append("\n\n");
                
                details.append("Disponibilité: ").append(disponibilite.getType()).append("\n");
                details.append("Horaires:\n");
                for (LocalDateTime horaire : disponibilite.getHoraires()) {
                    details.append("  - ").append(horaire.getHour()).append(":")
                          .append(String.format("%02d", horaire.getMinute())).append("\n");
                }
                
                details.append("\nItinéraire:\n");
                for (Communes commune : disponibilite.getItineraire().getPoints()) {
                    details.append("  - ").append(commune).append("\n");
                }
                
                if (profile.getStatut() == StatusUser.CHAUFFEUR) {
                    details.append("\nNombre de places disponibles: ")
                          .append(profile.getnbPlaces()).append("\n");
                }
                
                details.append("\nPréférences:\n");
                Preferences prefs = profile.getPreferences();
                details.append("  - Sexe: ").append(prefs.getSexePreferences()).append("\n");
                details.append("  - Musique: ").append(prefs.getMusiquePreferences()).append("\n");
                details.append("  - Bagages: ").append(prefs.getBagagesPreferences());
                
                itineraryDetails.setText(details.toString());
            } else {
                itineraryDetails.clear();
            }
        });
        
        // Initial load of users
        userListView.getItems().setAll(admin.getUtilisateurs());
        
        tab.setContent(itinerariesLayout);
        return tab;
    }
    
    // Statistics pane for the stats tab
    private TitledPane createStatsPane() {
        TitledPane pane = new TitledPane();
        pane.setText("Statistiques générales");
        pane.setCollapsible(true);
        
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        // Count metrics row with real data
        HBox countsBox = new HBox(20);
        
        VBox usersCountBox = createStatBox("Total utilisateurs", String.valueOf(admin.getNombreTotalUser()));
        VBox coursesCountBox = createStatBox("Total courses", String.valueOf(admin.getHistoriqueCourses().size() + admin.getCoursesEnCours().size()));
        VBox bannedUsersBox = createStatBox("Utilisateurs bannis", String.valueOf(admin.getUtilisateursBannis().size()));
        
        countsBox.getChildren().addAll(usersCountBox, coursesCountBox, bannedUsersBox);
        
        // Create charts section
        Label chartsLabel = new Label("Visualisation des données");
        chartsLabel.getStyleClass().add("section-header");
        
        // Add user distribution chart
        PieChart userDistributionChart = createUserDistributionChart();
        userDistributionChart.setTitle("Distribution des utilisateurs");
        userDistributionChart.setPrefHeight(300);
        
        // Add reputation chart
        BarChart<String, Number> reputationChart = createReputationChart();
        reputationChart.setTitle("Réputation moyenne par catégorie");
        reputationChart.setPrefHeight(300);
        
        HBox chartContainer = new HBox(20);
        chartContainer.setAlignment(Pos.CENTER);
        chartContainer.getChildren().addAll(userDistributionChart, reputationChart);
        
        // Button to show category activity
        Button showCategoryActivityButton = createIconButton("Afficher catégories les plus actives", "📊", "Détails des catégories les plus actives");
        showCategoryActivityButton.setOnAction(e -> {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Catégories les plus actives");
            dialog.setHeaderText("Les catégories d'utilisateurs classées par activité");
            
            // Calculate activity stats for user types
            int etudiantsCount = 0;
            int enseignantsCount = 0;
            int atsCount = 0;
            
            for (User user : admin.getUtilisateurs()) {
                if (user instanceof Etudiant) etudiantsCount++;
                else if (user instanceof Enseignant) enseignantsCount++;
                else if (user instanceof ATS) atsCount++;
            }
            
            // Create a bar chart for the dialog
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("Nombre d'utilisateurs par catégorie");
            xAxis.setLabel("Catégorie");
            yAxis.setLabel("Nombre d'utilisateurs");

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Utilisateurs");
            series.getData().add(new XYChart.Data<>("Étudiants", etudiantsCount));
            series.getData().add(new XYChart.Data<>("Enseignants", enseignantsCount));
            series.getData().add(new XYChart.Data<>("ATS", atsCount));
            
            barChart.getData().add(series);
            barChart.setPrefSize(500, 400);
            
            dialog.getDialogPane().setContent(barChart);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.show();
        });
        
        // Button to show faculty activity
        Button showFacultyActivityButton = createIconButton("Afficher facultés les plus actives", "🏫", "Statistiques par faculté");
        showFacultyActivityButton.setOnAction(e -> {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Facultés les plus actives");
            dialog.setHeaderText("Les facultés classées par activité");
            
            if (admin.getHistoriqueCourses().isEmpty() && admin.getCoursesEnCours().isEmpty()) {
                TextArea textArea = new TextArea("Aucune course n'a encore été créée. Les statistiques seront disponibles après la première course.");
                textArea.setEditable(false);
                dialog.getDialogPane().setContent(textArea);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.show();
                return;
            }
            
            // Count users by faculty
            Map<FacultesUSTHB, Integer> facultyCount = new HashMap<>();
            
            for (User user : admin.getUtilisateurs()) {
                FacultesUSTHB faculty = null;
                
                if (user instanceof Etudiant) {
                    faculty = ((Etudiant) user).getFaculte();
                } else if (user instanceof Enseignant) {
                    faculty = ((Enseignant) user).getFaculte();
                }
                
                if (faculty != null) {
                    facultyCount.put(faculty, facultyCount.getOrDefault(faculty, 0) + 1);
                }
            }
            
            // Create a bar chart for faculties
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("Utilisateurs par faculté");
            xAxis.setLabel("Faculté");
            yAxis.setLabel("Nombre d'utilisateurs");
            
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Utilisateurs");
            
            // Add data sorted by count
            facultyCount.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(entry -> {
                    series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
                });
            
            barChart.getData().add(series);
            barChart.setPrefSize(600, 400);
            
            dialog.getDialogPane().setContent(barChart);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.show();
        });
        
        VBox buttonsBox = new VBox(10);
        buttonsBox.getChildren().addAll(showCategoryActivityButton, showFacultyActivityButton);
        
        content.getChildren().addAll(countsBox, chartsLabel, chartContainer, new Separator(), buttonsBox);
        pane.setContent(content);
        
        return pane;
    }

    // Add these new methods for chart creation
    private PieChart createUserDistributionChart() {
        // Count users by type
        int etudiantsCount = 0;
        int enseignantsCount = 0;
        int atsCount = 0;
        int chauffeursCount = 0;
        int passagersCount = 0;
        
        for (User user : admin.getUtilisateurs()) {
            if (user instanceof Etudiant) etudiantsCount++;
            else if (user instanceof Enseignant) enseignantsCount++;
            else if (user instanceof ATS) atsCount++;
            
            if (user.getProfile().getStatut() == StatusUser.CHAUFFEUR) {
                chauffeursCount++;
            } else {
                passagersCount++;
            }
        }
        
        // Create pie chart data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Étudiants", etudiantsCount),
            new PieChart.Data("Enseignants", enseignantsCount),
            new PieChart.Data("ATS", atsCount)
        );
        
        PieChart chart = new PieChart(pieChartData);
        chart.setLabelsVisible(true);
        chart.setLegendVisible(true);
        chart.setLegendSide(Side.RIGHT);
        
        // Calculate total before the lambda so it's effectively final
        final int totalUsers = etudiantsCount + enseignantsCount + atsCount;
        
        // Add tooltips to pie chart slices
        pieChartData.forEach(data -> {
            String name = data.getName();
            double value = data.getPieValue();
            
            Tooltip tooltip = new Tooltip(String.format("%s: %.0f utilisateurs", name, value));
            Tooltip.install(data.getNode(), tooltip);
            
            // Add percentage to labels (now using totalUsers instead of calculating it inside)
            data.nameProperty().addListener((obs, oldVal, newVal) -> {
                double percentage = (value / totalUsers) * 100;
                data.setName(String.format("%s (%.1f%%)", name, percentage));
            });
        });
        
        return chart;
    }

    private BarChart<String, Number> createReputationChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 5, 1);
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        
        xAxis.setLabel("Catégorie");
        yAxis.setLabel("Réputation moyenne");
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Réputation");
        
        // Calculate average reputation by category
        double etudiantAvg = admin.getUtilisateurs().stream()
            .filter(u -> u instanceof Etudiant)
            .mapToDouble(User::getReputation)
            .average()
            .orElse(0);
        
        double enseignantAvg = admin.getUtilisateurs().stream()
            .filter(u -> u instanceof Enseignant)
            .mapToDouble(User::getReputation)
            .average()
            .orElse(0);
        
        double atsAvg = admin.getUtilisateurs().stream()
            .filter(u -> u instanceof ATS)
            .mapToDouble(User::getReputation)
            .average()
            .orElse(0);
        
        double chauffeurAvg = admin.getUtilisateurs().stream()
            .filter(u -> u.getProfile().getStatut() == StatusUser.CHAUFFEUR)
            .mapToDouble(User::getReputation)
            .average()
            .orElse(0);
        
        double passagerAvg = admin.getUtilisateurs().stream()
            .filter(u -> u.getProfile().getStatut() == StatusUser.PASSAGER)
            .mapToDouble(User::getReputation)
            .average()
            .orElse(0);
        
        series.getData().add(new XYChart.Data<>("Étudiants", etudiantAvg));
        series.getData().add(new XYChart.Data<>("Enseignants", enseignantAvg));
        series.getData().add(new XYChart.Data<>("ATS", atsAvg));
        series.getData().add(new XYChart.Data<>("Chauffeurs", chauffeurAvg));
        series.getData().add(new XYChart.Data<>("Passagers", passagerAvg));
        
        barChart.getData().add(series);
        
        // Add tooltips to bars
        series.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip(String.format("%s: %.2f/5", data.getXValue(), data.getYValue()));
            Tooltip.install(data.getNode(), tooltip);
        });
        
        return barChart;
    }
    
    // User ranking pane for stats tab
    private TitledPane createRankingPane() {
        TitledPane pane = new TitledPane();
        pane.setText("Classements d'utilisateurs");
        pane.setCollapsible(true);
        
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        // Top users button
        HBox topBox = new HBox(10);
        Button top10ChauffeursButton = new Button("Top 10 Chauffeurs");
        Button top10PassagersButton = new Button("Top 10 Passagers");
        Button pire10ChauffeursButton = new Button("Pire 10 Chauffeurs");
        Button pire10PassagersButton = new Button("Pire 10 Passagers");
        
        topBox.getChildren().addAll(top10ChauffeursButton, top10PassagersButton, pire10ChauffeursButton, pire10PassagersButton);
        
        // Most active users button
        Button mostActiveUsersButton = new Button("Utilisateurs les plus actifs");
        mostActiveUsersButton.setMaxWidth(Double.MAX_VALUE);
        
        // Action for top 10 chauffeurs
        top10ChauffeursButton.setOnAction(e -> showRankingDialog("Top 10 Chauffeurs", "top_chauffeurs"));
        
        // Action for top 10 passagers
        top10PassagersButton.setOnAction(e -> showRankingDialog("Top 10 Passagers", "top_passagers"));
        
        // Action for pire 10 chauffeurs
        pire10ChauffeursButton.setOnAction(e -> showRankingDialog("Pire 10 Chauffeurs", "pire_chauffeurs"));
        
        // Action for pire 10 passagers
        pire10PassagersButton.setOnAction(e -> showRankingDialog("Pire 10 Passagers", "pire_passagers"));
        
        // Action for most active users
        mostActiveUsersButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("20");
            dialog.setTitle("Utilisateurs les plus actifs");
            dialog.setHeaderText("Afficher les utilisateurs les plus actifs");
            dialog.setContentText("Nombre d'utilisateurs à afficher:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(limit -> {
                try {
                    int limitValue = Integer.parseInt(limit);
                    showRankingDialog("Top " + limitValue + " utilisateurs les plus actifs", "most_active", limitValue);
                } catch (NumberFormatException ex) {
                    showAlert("Erreur", "Veuillez entrer un nombre valide.");
                }
            });
        });
        
        content.getChildren().addAll(topBox, mostActiveUsersButton);
        pane.setContent(content);
        
        return pane;
    }
    
    // User management pane for stats tab
    private TitledPane createUserManagementPane() {
        TitledPane pane = new TitledPane();
        pane.setText("Gestion des utilisateurs");
        pane.setCollapsible(true);
        
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        // Banned users list
        Label bannedUsersLabel = new Label("Utilisateurs bannis");
        ListView<String> bannedUsersListView = new ListView<>();
        
        // Fill banned users list
        for (User user : admin.getUtilisateursBannis()) {
            bannedUsersListView.getItems().add(user.getNom() + " " + user.getPrenom() + " (" + user.getMatricule() + ")");
        }
        
        // Ban user button
        Button banUserButton = new Button("Bannir un utilisateur");
        banUserButton.setMaxWidth(Double.MAX_VALUE);
        banUserButton.setOnAction(e -> {
            ComboBox<User> userComboBox = new ComboBox<>();
            // Only show users with reputation <= 2
            userComboBox.setItems(FXCollections.observableArrayList(
                admin.getUtilisateurs().stream()
                    .filter(u -> u.getReputation() <= 2.0)
                    .collect(Collectors.toList())
            ));
            TextField searchField = new TextField();
            searchField.setPromptText("Rechercher par nom...");
            searchField.textProperty().addListener((obs, oldVal, newVal) -> {
                userComboBox.setItems(FXCollections.observableArrayList(
                    admin.getUtilisateurs().stream()
                        .filter(u -> u.getReputation() <= 2.0)
                        .filter(u -> (u.getNom() + " " + u.getPrenom()).toLowerCase().startsWith(newVal.toLowerCase()))
                        .collect(Collectors.toList())
                ));
            });
            Dialog<User> dialog = new Dialog<>();
            dialog.setTitle("Bannir un utilisateur");
            dialog.setHeaderText("Sélectionnez un utilisateur à bannir");
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(new Label("Utilisateur:"), 0, 0);
            grid.add(searchField, 1, 0);
            grid.add(userComboBox, 1, 1);
            dialogPane.setContent(grid);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    return userComboBox.getValue();
                }
                return null;
            });
            Optional<User> result = dialog.showAndWait();
            result.ifPresent(user -> {
                admin.bannirUtilisateur(user);
                // Refresh the banned users list
                bannedUsersListView.getItems().clear();
                for (User bannedUser : admin.getUtilisateursBannis()) {
                    bannedUsersListView.getItems().add(bannedUser.getNom() + " " + bannedUser.getPrenom() + " (" + bannedUser.getMatricule() + ")");
                }
            });
        });
        
        content.getChildren().addAll(bannedUsersLabel, bannedUsersListView, banUserButton);
        pane.setContent(content);
        
        return pane;
    }
    

    
    private void showRankingDialog(String title, String type) {
        showRankingDialog(title, type, 10);
    }
    
    private void showRankingDialog(String title, String type, int limit) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText("Classement des utilisateurs");
        
        StringBuilder content = new StringBuilder();
        List<User> userList = new ArrayList<>();
        
        switch (type) {
            case "top_chauffeurs":
                // Get actual top chauffeurs data
                userList = admin.getUtilisateurs().stream()
                    .filter(u -> u.getProfile().getStatut() == StatusUser.CHAUFFEUR)
                    .sorted((u1, u2) -> Double.compare(u2.getReputation(), u1.getReputation()))
                    .limit(10)
                    .collect(Collectors.toList());
                    
                content.append("=== Top 10 Chauffeurs ===\n\n");
                int rank1 = 1;
                for (User user : userList) {
                    content.append(rank1).append(". ").append(user.getNom()).append(" ").append(user.getPrenom())
                          .append(" - Réputation: ").append(String.format("%.1f", user.getReputation())).append("\n");
                    rank1++;
                }
                break;
                
            case "top_passagers":
                // Get actual top passagers data
                userList = admin.getUtilisateurs().stream()
                    .filter(u -> u.getProfile().getStatut() == StatusUser.PASSAGER)
                    .sorted((u1, u2) -> Double.compare(u2.getReputation(), u1.getReputation()))
                    .limit(10)
                    .collect(Collectors.toList());
                    
                content.append("=== Top 10 Passagers ===\n\n");
                int rank2 = 1;
                for (User user : userList) {
                    content.append(rank2).append(". ").append(user.getNom()).append(" ").append(user.getPrenom())
                          .append(" - Réputation: ").append(String.format("%.1f", user.getReputation())).append("\n");
                    rank2++;
                }
                break;
                
            case "pire_chauffeurs":
                // Get actual worst chauffeurs data
                userList = admin.getUtilisateurs().stream()
                    .filter(u -> u.getProfile().getStatut() == StatusUser.CHAUFFEUR)
                    .sorted((u1, u2) -> Double.compare(u1.getReputation(), u2.getReputation()))
                    .limit(10)
                    .collect(Collectors.toList());
                    
                content.append("=== Pire 10 Chauffeurs ===\n\n");
                int rank3 = 1;
                for (User user : userList) {
                    content.append(rank3).append(". ").append(user.getNom()).append(" ").append(user.getPrenom())
                          .append(" - Réputation: ").append(String.format("%.1f", user.getReputation())).append("\n");
                    rank3++;
                }
                break;
                
            case "pire_passagers":
                // Get actual worst passagers data
                userList = admin.getUtilisateurs().stream()
                    .filter(u -> u.getProfile().getStatut() == StatusUser.PASSAGER)
                    .sorted((u1, u2) -> Double.compare(u1.getReputation(), u2.getReputation()))
                    .limit(10)
                    .collect(Collectors.toList());
                    
                content.append("=== Pire 10 Passagers ===\n\n");
                int rank4 = 1;
                for (User user : userList) {
                    content.append(rank4).append(". ").append(user.getNom()).append(" ").append(user.getPrenom())
                          .append(" - Réputation: ").append(String.format("%.1f", user.getReputation())).append("\n");
                    rank4++;
                }
                break;
                
            case "most_active":
                // For activity, we'll use the number of courses (this would normally be
                // calculated in reality based on user history)
                // Since this is just a stub without real course data, we'll sort by user ID for demo
                userList = admin.getUtilisateurs().stream()
                    .sorted((u1, u2) -> u1.getMatricule().compareTo(u2.getMatricule()))
                    .limit(limit)
                    .collect(Collectors.toList());
                    
                content.append("=== Utilisateurs les plus actifs (Top " + limit + ") ===\n\n");
                int rank5 = 1;
                for (User user : userList) {
                    int activity = Math.abs(user.getMatricule().hashCode() % 15) + 1; // Just a demo value
                    content.append(rank5).append(". ").append(user.getNom()).append(" ").append(user.getPrenom())
                          .append(" - ").append(activity).append(" trajets\n");
                    rank5++;
                }
                break;
        }
        
        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false);
        textArea.setPrefWidth(400);
        textArea.setPrefHeight(300);
        
        dialog.getDialogPane().setContent(textArea);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.show();
    }
    
    private void setupCoursesTable(TableView<Course> table) {
        // Create columns for the courses table
        TableColumn<Course, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> {
            Integer id = cellData.getValue().getCourseId();
            return new javafx.beans.property.SimpleObjectProperty<>(id);
        });
        
        TableColumn<Course, String> passengerColumn = new TableColumn<>("Passager");
        passengerColumn.setCellValueFactory(cellData -> {
            User passenger = cellData.getValue().getPassenger();
            String passengerName = passenger.getNom() + " " + passenger.getPrenom();
            return new javafx.beans.property.SimpleStringProperty(passengerName);
        });
        
        TableColumn<Course, String> driverColumn = new TableColumn<>("Chauffeur");
        driverColumn.setCellValueFactory(cellData -> {
            User driver = cellData.getValue().getDriver();
            String driverName = driver.getNom() + " " + driver.getPrenom();
            return new javafx.beans.property.SimpleStringProperty(driverName);
        });
        
        TableColumn<Course, String> statusColumn = new TableColumn<>("Statut");
        statusColumn.setCellValueFactory(cellData -> {
            CourseStatus status = cellData.getValue().getStatusCourse();
            return new javafx.beans.property.SimpleStringProperty(status.toString());
        });
        
        table.getColumns().addAll(idColumn, passengerColumn, driverColumn, statusColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    private void refreshCoursesTables() {
        // Clear tables
        ongoingCoursesTable.getItems().clear();
        historyCoursesTable.getItems().clear();
        
        // Add ongoing courses
        ongoingCoursesTable.getItems().addAll(admin.getCoursesEnCours());
        
        // Add history courses
        historyCoursesTable.getItems().addAll(admin.getHistoriqueCourses());
    }
    
    private void endSelectedCourse() {
        Course course = ongoingCoursesTable.getSelectionModel().getSelectedItem();
        if (course == null) {
            showAlert("Erreur", "Veuillez sélectionner une course.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Terminer la course");
        alert.setHeaderText("Êtes-vous sûr de vouloir terminer cette course?");
        alert.setContentText("Cela va terminer la course et permettre l'évaluation.");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // End the course
            course.terminerLaCourse();
            
            // Update admin's course lists
            admin.courseterminé(course);
            
            // Decrement available places for driver
            if (course.getDriver().getProfile().getStatut() == StatusUser.CHAUFFEUR) {
                int currentPlaces = course.getDriver().getProfile().getnbPlaces();
                if (currentPlaces > 0) {
                    course.getDriver().getProfile().decrementNbPlaces();
                }
            }
            
            // Refresh tables
            refreshCoursesTables();
            
            // Open rating dialog
            showRatingDialog(course);
        }
    }
    
    private void viewCourseDetails(TableView<Course> table) {
        Course selectedCourse = table.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) {
            showAlert("Erreur", "Veuillez sélectionner une course.");
            return;
        }
        
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Détails de la course");
        dialog.setHeaderText("Course #" + selectedCourse.getCourseId());
        
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.getStyleClass().add("course-details-container");
        
        // Course status with visual indicator
        HBox statusContainer = new HBox(10);
        statusContainer.setAlignment(Pos.CENTER_LEFT);
        
        Label statusLabel = new Label("Statut:");
        statusLabel.getStyleClass().add("detail-label");
        
        Label statusValue = new Label(selectedCourse.getStatusCourse().toString());
        statusValue.getStyleClass().add("status-badge");
        if (selectedCourse.getStatusCourse() == CourseStatus.EN_COURS) {
            statusValue.getStyleClass().add("status-ongoing");
        } else {
            statusValue.getStyleClass().add("status-completed");
        }
        
        statusContainer.getChildren().addAll(statusLabel, statusValue);
        content.getChildren().add(statusContainer);
        
        // Passenger info card
        TitledPane passengerPane = new TitledPane();
        passengerPane.setText("Passager");
        passengerPane.getStyleClass().add("user-card");
        
        User passenger = selectedCourse.getPassenger();
        VBox passengerInfo = createUserInfoBox(passenger);
        passengerPane.setContent(passengerInfo);
        
        // Driver info card
        TitledPane driverPane = new TitledPane();
        driverPane.setText("Chauffeur");
        driverPane.getStyleClass().add("user-card");
        
        User driver = selectedCourse.getDriver();
        VBox driverInfo = createUserInfoBox(driver);
        driverPane.setContent(driverInfo);
        
        content.getChildren().addAll(passengerPane, driverPane);
        
        // If course is completed, show reviews
        if (selectedCourse.getStatusCourse() == CourseStatus.TERMINEE) {
            TitledPane reviewsPane = new TitledPane();
            reviewsPane.setText("Évaluations");
            
            VBox reviewsContent = new VBox(10);
            reviewsContent.setPadding(new Insets(10));
            
            boolean foundPassengerReview = false;
            boolean foundDriverReview = false;
            
            // Look for driver reviews from this passenger
            for (Review review : driver.getReceivedReviews()) {
                if (review.getReviewer().equals(passenger)) {
                    VBox reviewBox = new VBox(5);
                    reviewBox.getStyleClass().add("review-box");
                    
                    Label reviewTitle = new Label("Avis du passager sur le chauffeur:");
                    reviewTitle.getStyleClass().add("review-title");
                    
                    // Create star rating visual
                    HBox starRating = new HBox(2);
                    for (int i = 0; i < review.getRating(); i++) {
                        Label star = new Label("★");
                        star.getStyleClass().add("star-filled");
                        starRating.getChildren().add(star);
                    }
                    
                    Label reviewText = new Label(review.toString());
                    reviewText.setWrapText(true);
                    
                    reviewBox.getChildren().addAll(reviewTitle, starRating, reviewText);
                    reviewsContent.getChildren().add(reviewBox);
                    foundPassengerReview = true;
                    break;
                }
            }
            
            if (!foundPassengerReview) {
                Label noReview = new Label("Aucun avis du passager sur le chauffeur.");
                noReview.getStyleClass().add("no-review-label");
                reviewsContent.getChildren().add(noReview);
            }
            
            // Look for passenger reviews from this driver
            for (Review review : passenger.getReceivedReviews()) {
                if (review.getReviewer().equals(driver)) {
                    VBox reviewBox = new VBox(5);
                    reviewBox.getStyleClass().add("review-box");
                    
                    Label reviewTitle = new Label("Avis du chauffeur sur le passager:");
                    reviewTitle.getStyleClass().add("review-title");
                    
                    // Create star rating visual
                    HBox starRating = new HBox(2);
                    for (int i = 0; i < review.getRating(); i++) {
                        Label star = new Label("★");
                        star.getStyleClass().add("star-filled");
                        starRating.getChildren().add(star);
                    }
                    
                    Label reviewText = new Label(review.toString());
                    reviewText.setWrapText(true);
                    
                    reviewBox.getChildren().addAll(reviewTitle, starRating, reviewText);
                    reviewsContent.getChildren().add(reviewBox);
                    foundDriverReview = true;
                    break;
                }
            }
            
            if (!foundDriverReview) {
                Label noReview = new Label("Aucun avis du chauffeur sur le passager.");
                noReview.getStyleClass().add("no-review-label");
                reviewsContent.getChildren().add(noReview);
            }
            
            reviewsPane.setContent(reviewsContent);
            content.getChildren().add(reviewsPane);
        }
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    // Helper method to create consistent user info displays
    private VBox createUserInfoBox(User user) {
        VBox infoBox = new VBox(5);
        infoBox.setPadding(new Insets(10));
        
        HBox nameBox = new HBox(10);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        
        Label nameLabel = new Label(user.getNom() + " " + user.getPrenom());
        nameLabel.getStyleClass().add("user-name");
        
        Label typeLabel = new Label(getUserType(user));
        typeLabel.getStyleClass().add("user-type-badge");
        
        nameBox.getChildren().addAll(nameLabel, typeLabel);
        
        // Reputation with stars
        HBox reputationBox = new HBox(5);
        reputationBox.setAlignment(Pos.CENTER_LEFT);
        
        Label repLabel = new Label("Réputation: ");
        
        Label repValue = new Label(String.format("%.1f", user.getReputation()));
        repValue.getStyleClass().add("reputation-value");
        
        reputationBox.getChildren().addAll(repLabel, repValue);
        
        // Add visual stars
        for (int i = 0; i < Math.round(user.getReputation()); i++) {
            Label star = new Label("★");
            star.getStyleClass().add("star-filled");
            reputationBox.getChildren().add(star);
        }
        
        // Matricule
        Label matriculeLabel = new Label("Matricule: " + user.getMatricule());
        
        infoBox.getChildren().addAll(nameBox, reputationBox, matriculeLabel);
        
        return infoBox;
    }

    private String getUserType(User user) {
        if (user instanceof Etudiant) return "Étudiant";
        if (user instanceof Enseignant) return "Enseignant";
        if (user instanceof ATS) return "ATS";
        return "Utilisateur";
    }
    
    private void showRatingDialog(Course course) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Évaluer la course");
        dialog.setHeaderText("Course #" + course.getCourseId() + " terminée");
        
        TabPane ratingTabPane = new TabPane();
        
        // Tab for passenger rating driver
        Tab passengerRatingTab = createRatingTab(course.getPassenger(), course.getDriver(), course, false);
        passengerRatingTab.setText("Passager → Chauffeur");
        
        // Tab for driver rating passenger
        Tab driverRatingTab = createRatingTab(course.getDriver(), course.getPassenger(), course, true);
        driverRatingTab.setText("Chauffeur → Passager");
        
        ratingTabPane.getTabs().addAll(passengerRatingTab, driverRatingTab);
        
        dialog.getDialogPane().setContent(ratingTabPane);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }
    
    private Tab createRatingTab(User reviewer, User reviewee, Course course, boolean isDriverRatingPassenger) {
        Tab tab = new Tab();
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        // Rating
        grid.add(new Label("Note (1-5):"), 0, 0);
        Spinner<Integer> ratingSpinner = new Spinner<>(1, 5, 5);
        ratingSpinner.getStyleClass().add("rating-spinner");
        grid.add(ratingSpinner, 1, 0);
        
        // Comment
        grid.add(new Label("Commentaire:"), 0, 1);
        TextArea commentArea = new TextArea();
        commentArea.setPrefRowCount(5);
        commentArea.setPromptText("Donnez votre avis sur cette expérience...");
        grid.add(commentArea, 1, 1);
        
        // Submit button
        Button submitButton = createIconButton("Soumettre l'évaluation", "✓", "Envoyer votre évaluation");
        
        submitButton.setOnAction(e -> {
            // Get rating and comment
            int rating = ratingSpinner.getValue();
            String comment = commentArea.getText().trim();
            if (comment.isEmpty()) {
                comment = "Aucun commentaire fourni";
            }
            
            // Create the review object
            Review review = new Review(rating, comment, reviewer, reviewee);
            
            // Add the review to the reviewee
            reviewee.addReview(review);
            
            // Update UI to reflect new reputation
            updateUserList();
            
            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Évaluation soumise");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Votre évaluation a été soumise avec succès!");
            successAlert.showAndWait();
            
            // Disable button after submission
            submitButton.setDisable(true);
        });
        
        grid.add(submitButton, 1, 2);
        GridPane.setHalignment(submitButton, javafx.geometry.HPos.RIGHT);
        
        tab.setContent(grid);
        return tab;
    }
    
    private void showDemandeDeCourseDialog() {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Nouvelle Demande de Course");
        dialog.setHeaderText("Créer une nouvelle demande de course");

        // Set the button types
        ButtonType confirmButtonType = new ButtonType("Rechercher Chauffeurs", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        // Create content
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.getStyleClass().add("demand-dialog");

        // Passenger selection
        Label passengerLabel = new Label("Sélectionnez un passager:");
        passengerLabel.getStyleClass().add("section-header");
        
        // Filter for passengers only
        List<User> passengers = admin.getUtilisateurs().stream()
            .filter(u -> u.getProfile().getStatut() == StatusUser.PASSAGER)
            .collect(Collectors.toList());
        
        ComboBox<User> passengerComboBox = new ComboBox<>();
        passengerComboBox.setItems(FXCollections.observableArrayList(passengers));
        passengerComboBox.setCellFactory(lv -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getNom() + " " + user.getPrenom() + " (" + user.getMatricule() + ")");
                }
            }
        });
        passengerComboBox.setButtonCell(new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getNom() + " " + user.getPrenom() + " (" + user.getMatricule() + ")");
                }
            }
        });
        
        // Itinerary section
        Label itineraryLabel = new Label("Informations sur la course:");
        itineraryLabel.getStyleClass().add("section-header");
        
        GridPane itineraryGrid = new GridPane();
        itineraryGrid.setHgap(10);
        itineraryGrid.setVgap(10);
        
        // Communes selection
        itineraryGrid.add(new Label("Point de départ:"), 0, 0);
        ComboBox<Communes> departComboBox = new ComboBox<>(
            FXCollections.observableArrayList(Communes.values())
        );
        departComboBox.setValue(Communes.USTHB);
        itineraryGrid.add(departComboBox, 1, 0);
        
        itineraryGrid.add(new Label("Destination:"), 0, 1);
        ComboBox<Communes> destinationComboBox = new ComboBox<>(
            FXCollections.observableArrayList(Communes.values())
        );
        destinationComboBox.setValue(Communes.SIDI_M_HAMED);
        itineraryGrid.add(destinationComboBox, 1, 1);
        
        // Course type
        itineraryGrid.add(new Label("Type de course:"), 0, 2);
        ComboBox<TypeCourse> typeCourseComboBox = new ComboBox<>(
            FXCollections.observableArrayList(TypeCourse.values())
        );
        typeCourseComboBox.setValue(TypeCourse.ALLER_SIMPLE);
        itineraryGrid.add(typeCourseComboBox, 1, 2);
        
        content.getChildren().addAll(
            passengerLabel, 
            passengerComboBox, 
            new Separator(),
            itineraryLabel,
            itineraryGrid
        );
        
        // Update button state based on selection
        Node confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
        confirmButton.setDisable(true);
        
        passengerComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            confirmButton.setDisable(newVal == null);
        });
        
        dialog.getDialogPane().setContent(content);
        
        // Convert the result
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                User selectedPassenger = passengerComboBox.getValue();
                if (selectedPassenger != null) {
                    // Update the passenger's profile with the selected itinerary
                    Itineraire itineraire = new Itineraire(List.of(
                        departComboBox.getValue(), 
                        destinationComboBox.getValue()
                    ));
                    
                    selectedPassenger.getProfile().getDisponibilite().setItineraire(itineraire);
                    selectedPassenger.getProfile().setTypeCourse(typeCourseComboBox.getValue());
                    
                    return selectedPassenger;
                }
            }
            return null;
        });

        Optional<User> result = dialog.showAndWait();
        
        result.ifPresent(this::findMatchingDrivers);
    }

    private void findMatchingDrivers(User passenger) {
        // Create a list to store matching drivers
        List<User> matchingDrivers = new ArrayList<>();
        
        // Find all drivers that match with this passenger
        for (User driver : admin.getUtilisateurs()) {
            if (driver.getProfile().getStatut() == StatusUser.CHAUFFEUR) {
                if (DemandeDeCourse.canMatch(passenger, driver)) {
                    matchingDrivers.add(driver);
                }
            }
        }
        
        // Show error if no matching drivers
        if (matchingDrivers.isEmpty()) {
            String reason;
            long totalChauffeurs = admin.getUtilisateurs().stream().filter(u -> u.getProfile().getStatut() == StatusUser.CHAUFFEUR).count();
            if (totalChauffeurs == 0) {
                reason = "Il n'y a actuellement aucun chauffeur inscrit dans le système.";
                showAlert("Aucun chauffeur compatible", reason);
                return;
            } else {
                // Show dialog to change preferences
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Aucun chauffeur trouvé");
                dialog.setHeaderText("Aucun chauffeur ne correspond à l'itinéraire ou aux préférences du passager sélectionné.\nPeut-être changer vos préférences pour chercher encore une fois !");
                
                // Preference ComboBoxes
                Preferences prefs = passenger.getProfile().getPreferences();
                ComboBox<SexePreferences> sexeBox = new ComboBox<>(FXCollections.observableArrayList(SexePreferences.values()));
                sexeBox.setValue(prefs.getSexePreferences());
                ComboBox<MusiquePreferences> musiqueBox = new ComboBox<>(FXCollections.observableArrayList(MusiquePreferences.values()));
                musiqueBox.setValue(prefs.getMusiquePreferences());
                ComboBox<BagagesPreferences> bagagesBox = new ComboBox<>(FXCollections.observableArrayList(BagagesPreferences.values()));
                bagagesBox.setValue(prefs.getBagagesPreferences());
                
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.add(new Label("Préférence de sexe:"), 0, 0);
                grid.add(sexeBox, 1, 0);
                grid.add(new Label("Préférence de musique:"), 0, 1);
                grid.add(musiqueBox, 1, 1);
                grid.add(new Label("Préférence de bagages:"), 0, 2);
                grid.add(bagagesBox, 1, 2);
                dialog.getDialogPane().setContent(grid);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                
                dialog.showAndWait().ifPresent(result -> {
                    if (result == ButtonType.OK) {
                        prefs.setSexePreferences(sexeBox.getValue());
                        prefs.setMusiquePreferences(musiqueBox.getValue());
                        prefs.setBagagesPreferences(bagagesBox.getValue());
                        // Try again with new preferences
                        findMatchingDrivers(passenger);
                    }
                });
                return;
            }
        }
        
        // Create dialog to select a driver
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Chauffeurs Disponibles");
        dialog.setHeaderText("Sélectionner un chauffeur pour " + 
                             passenger.getNom() + " " + passenger.getPrenom());
        
        // Set the button types
        ButtonType selectButtonType = new ButtonType("Sélectionner", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(selectButtonType, ButtonType.CANCEL);
        
        // Create a TableView to display drivers
        TableView<User> driverTable = new TableView<>();
        
        // Create columns
        TableColumn<User, String> matriculeCol = new TableColumn<>("Matricule");
        matriculeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMatricule()));
        
        TableColumn<User, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(data -> new SimpleStringProperty(
            data.getValue().getNom() + " " + data.getValue().getPrenom()));
        
        TableColumn<User, String> reputationCol = new TableColumn<>("Réputation");
        reputationCol.setCellValueFactory(data -> new SimpleStringProperty(
            String.format("%.1f", data.getValue().getReputation())));
        
        TableColumn<User, String> placesCol = new TableColumn<>("Places");
        placesCol.setCellValueFactory(data -> new SimpleStringProperty(
            String.valueOf(data.getValue().getProfile().getnbPlaces())));
        
        driverTable.getColumns().addAll(matriculeCol, nomCol, reputationCol, placesCol);
        driverTable.setItems(FXCollections.observableArrayList(matchingDrivers));
        driverTable.setPrefHeight(300);
        driverTable.setPrefWidth(450);
        
        // Layout
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(new Label("Chauffeurs disponibles:"), driverTable);
        vBox.setPadding(new Insets(20));
        
        dialog.getDialogPane().setContent(vBox);
        
        // Enable/disable select button depending on selection
        Node selectButton = dialog.getDialogPane().lookupButton(selectButtonType);
        selectButton.setDisable(true);
        
        driverTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                selectButton.setDisable(newValue == null);
            }
        );
        
        // Convert the result to selected driver
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == selectButtonType) {
                return driverTable.getSelectionModel().getSelectedItem();
            }
            return null;
        });
        
        // Show dialog and process result
        Optional<User> result = dialog.showAndWait();
        
        result.ifPresent(driver -> {
            createCourse(passenger, driver);
        });
    }

    private void createCourse(User passenger, User driver) {
        // Create a new course
        Course course = new Course(passenger, driver);
        course.acceptCourse();
        
        // Add to admin's ongoing courses
        admin.ajoutercourse(course);
        
        // Update UI
        refreshCoursesTables();
        
        // Decrement available places for driver
        driver.getProfile().decrementNbPlaces();
        
        // Show confirmation
        showAlert("Course créée", 
                 "Une course a été créée entre le passager " + passenger.getNom() + 
                 " et le chauffeur " + driver.getNom());
    }
    
    private Tab createEtudiantTab() {
        Tab tab = new Tab("Étudiant");
        tab.setClosable(false);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Common user fields
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");
        TextField matriculeField = new TextField();
        matriculeField.setPromptText("Matricule");
        
        // Status user selection
        ComboBox<StatusUser> statusComboBox = new ComboBox<>(
            FXCollections.observableArrayList(StatusUser.values())
        );
        statusComboBox.setPromptText("Statut");
        
        // Student-specific fields
        TextField anneeAdmissionField = new TextField();
        anneeAdmissionField.setPromptText("Année d'admission");
        
        ComboBox<FacultesUSTHB> faculteComboBox = new ComboBox<>(
            FXCollections.observableArrayList(FacultesUSTHB.values())
        );
        faculteComboBox.setPromptText("Faculté");
        
        ComboBox<Specialite> specialiteComboBox = new ComboBox<>(
            FXCollections.observableArrayList(Specialite.values())
        );
        specialiteComboBox.setPromptText("Spécialité");
        
        // Preferences
        TitledPane preferencesPane = createPreferencesPane();
        
        // Itinerary - pass the status ComboBox to dynamically update based on selected status
        TitledPane itineraryPane = createItineraryPane(statusComboBox);
        
        // Add button
        Button addButton = new Button("Ajouter Étudiant");
        addButton.setOnAction(e -> {
            try {
                // Validate required fields
                if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || 
                    matriculeField.getText().isEmpty() || statusComboBox.getValue() == null ||
                    anneeAdmissionField.getText().isEmpty() || faculteComboBox.getValue() == null ||
                    specialiteComboBox.getValue() == null) {
                    showAlert("Erreur", "Veuillez remplir tous les champs obligatoires.");
                    return;
                }
                
                // Create profile with basic preferences
                Profile profile = createBasicProfile(statusComboBox.getValue());
                
                // Create student
                Etudiant etudiant = new Etudiant(
                    nomField.getText(),
                    prenomField.getText(),
                    matriculeField.getText(),
                    5.0, // Default reputation
                    profile,
                    Integer.parseInt(anneeAdmissionField.getText()),
                    faculteComboBox.getValue(),
                    specialiteComboBox.getValue()
                );
                
                // Add to admin and update list
                admin.ajouterUtilisateur(etudiant);
                updateUserList();
                
                // Clear fields
                clearFields(nomField, prenomField, matriculeField, anneeAdmissionField);
                statusComboBox.setValue(null);
                faculteComboBox.setValue(null);
                specialiteComboBox.setValue(null);
                
            } catch (Exception ex) {
                showAlert("Erreur", "Veuillez remplir tous les champs correctement: " + ex.getMessage());
            }
        });
        
        // Add fields to grid
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Prénom:"), 0, 1);
        grid.add(prenomField, 1, 1);
        grid.add(new Label("Matricule:"), 0, 2);
        grid.add(matriculeField, 1, 2);
        grid.add(new Label("Statut:"), 0, 3);
        grid.add(statusComboBox, 1, 3);
        grid.add(new Label("Année d'admission:"), 0, 4);
        grid.add(anneeAdmissionField, 1, 4);
        grid.add(new Label("Faculté:"), 0, 5);
        grid.add(faculteComboBox, 1, 5);
        grid.add(new Label("Spécialité:"), 0, 6);
        grid.add(specialiteComboBox, 1, 6);
        grid.add(preferencesPane, 0, 7, 2, 1);
        grid.add(itineraryPane, 0, 8, 2, 1);
        grid.add(addButton, 0, 9, 2, 1);
        
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        
        tab.setContent(scrollPane);
        return tab;
    }
    
    private Tab createEnseignantTab() {
        Tab tab = new Tab("Enseignant");
        tab.setClosable(false);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Common user fields
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");
        TextField matriculeField = new TextField();
        matriculeField.setPromptText("Matricule");
        
        // Status user selection
        ComboBox<StatusUser> statusComboBox = new ComboBox<>(
            FXCollections.observableArrayList(StatusUser.values())
        );
        statusComboBox.setPromptText("Statut");
        
        // Enseignant-specific fields
        TextField anneeRecrutementField = new TextField();
        anneeRecrutementField.setPromptText("Année de recrutement");
        
        ComboBox<FacultesUSTHB> faculteComboBox = new ComboBox<>(
            FXCollections.observableArrayList(FacultesUSTHB.values())
        );
        faculteComboBox.setPromptText("Faculté");
        
        // Preferences
        TitledPane preferencesPane = createPreferencesPane();
        
        // Itinerary - pass the status ComboBox
        TitledPane itineraryPane = createItineraryPane(statusComboBox);
        
        // Add button
        Button addButton = new Button("Ajouter Enseignant");
        addButton.setOnAction(e -> {
            try {
                // Validate required fields
                if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || 
                    matriculeField.getText().isEmpty() || statusComboBox.getValue() == null ||
                    anneeRecrutementField.getText().isEmpty() || faculteComboBox.getValue() == null) {
                    showAlert("Erreur", "Veuillez remplir tous les champs obligatoires.");
                    return;
                }
                
                // Create profile with basic preferences
                Profile profile = createBasicProfile(statusComboBox.getValue());
                
                // Create Enseignant
               // Create Enseignant
            Enseignant enseignant = new Enseignant(
                nomField.getText(),
                prenomField.getText(),
                matriculeField.getText(),
                5.0, // Default reputation
                profile,
                Integer.parseInt(anneeRecrutementField.getText()),
                faculteComboBox.getValue()
            );
                
                // Add to admin and update list
                admin.ajouterUtilisateur(enseignant);
                updateUserList();
                
                // Clear fields
                clearFields(nomField, prenomField, matriculeField, anneeRecrutementField);
                statusComboBox.setValue(null);
                faculteComboBox.setValue(null);
                
            } catch (Exception ex) {
                showAlert("Erreur", "Veuillez remplir tous les champs correctement: " + ex.getMessage());
            }
        });
        
        // Add fields to grid
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Prénom:"), 0, 1);
        grid.add(prenomField, 1, 1);
        grid.add(new Label("Matricule:"), 0, 2);
        grid.add(matriculeField, 1, 2);
        grid.add(new Label("Statut:"), 0, 3);
        grid.add(statusComboBox, 1, 3);
        grid.add(new Label("Année de recrutement:"), 0, 4);
        grid.add(anneeRecrutementField, 1, 4);
        grid.add(new Label("Faculté:"), 0, 5);
        grid.add(faculteComboBox, 1, 5);
        grid.add(preferencesPane, 0, 6, 2, 1);
        grid.add(itineraryPane, 0, 7, 2, 1);
        grid.add(addButton, 0, 8, 2, 1);
        
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        
        tab.setContent(scrollPane);
        return tab;
    }
    
    // Add this method to create the ATS tab
    private Tab createATSTab() {
        Tab tab = new Tab("ATS");
        tab.setClosable(false);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Common user fields
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");
        TextField matriculeField = new TextField();
        matriculeField.setPromptText("Matricule");
        
        // Status user selection
        ComboBox<StatusUser> statusComboBox = new ComboBox<>(
            FXCollections.observableArrayList(StatusUser.values())
        );
        statusComboBox.setPromptText("Statut");
        
        // ATS-specific fields
        TextField anneeRecrutementField = new TextField();
        anneeRecrutementField.setPromptText("Année de recrutement");
        
        TextField serviceField = new TextField();
        serviceField.setPromptText("Service de rattachement");
        
        // Preferences
        TitledPane preferencesPane = createPreferencesPane();
        
        // Itinerary - pass the status ComboBox
        TitledPane itineraryPane = createItineraryPane(statusComboBox);
        
        // Add button
        Button addButton = new Button("Ajouter ATS");
        addButton.setOnAction(e -> {
            try {
                // Validate required fields
                if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || 
                    matriculeField.getText().isEmpty() || statusComboBox.getValue() == null ||
                    anneeRecrutementField.getText().isEmpty() || serviceField.getText().isEmpty()) {
                    showAlert("Erreur", "Veuillez remplir tous les champs obligatoires.");
                    return;
                }
                
                // Create profile with basic preferences
                Profile profile = createBasicProfile(statusComboBox.getValue());
                
                // Create ATS
                ATS ats = new ATS(
                    nomField.getText(),
                    prenomField.getText(),
                    matriculeField.getText(),
                    5.0, // Default reputation
                    profile,
                    Integer.parseInt(anneeRecrutementField.getText()),
                    serviceField.getText()
                );
                
                // Add to admin and update list
                admin.ajouterUtilisateur(ats);
                updateUserList();
                
                // Clear fields
                clearFields(nomField, prenomField, matriculeField, anneeRecrutementField, serviceField);
                statusComboBox.setValue(null);
                
            } catch (Exception ex) {
                showAlert("Erreur", "Veuillez remplir tous les champs correctement: " + ex.getMessage());
            }
        });
        
        // Add fields to grid
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Prénom:"), 0, 1);
        grid.add(prenomField, 1, 1);
        grid.add(new Label("Matricule:"), 0, 2);
        grid.add(matriculeField, 1, 2);
        grid.add(new Label("Statut:"), 0, 3);
        grid.add(statusComboBox, 1, 3);
        grid.add(new Label("Année de recrutement:"), 0, 4);
        grid.add(anneeRecrutementField, 1, 4);
        grid.add(new Label("Service:"), 0, 5);
        grid.add(serviceField, 1, 5);
        grid.add(preferencesPane, 0, 6, 2, 1);
        grid.add(itineraryPane, 0, 7, 2, 1);
        grid.add(addButton, 0, 8, 2, 1);
        
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        
        tab.setContent(scrollPane);
        return tab;
    }

    private TitledPane createPreferencesPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Preferences
        ComboBox<SexePreferences> sexePrefComboBox = new ComboBox<>(
            FXCollections.observableArrayList(SexePreferences.values())
        );
        sexePrefComboBox.setValue(SexePreferences.SANS_PREFERENCE);
        
        ComboBox<MusiquePreferences> musiquePrefComboBox = new ComboBox<>(
            FXCollections.observableArrayList(MusiquePreferences.values())
        );
        musiquePrefComboBox.setValue(MusiquePreferences.SANS_PREFERENCE);
        
        ComboBox<BagagesPreferences> bagagesPrefComboBox = new ComboBox<>(
            FXCollections.observableArrayList(BagagesPreferences.values())
        );
        bagagesPrefComboBox.setValue(BagagesPreferences.SANS_PREFERENCE);
        
        grid.add(new Label("Préférence de sexe:"), 0, 0);
        grid.add(sexePrefComboBox, 1, 0);
        grid.add(new Label("Préférence de musique:"), 0, 1);
        grid.add(musiquePrefComboBox, 1, 1);
        grid.add(new Label("Préférence de bagages:"), 0, 2);
        grid.add(bagagesPrefComboBox, 1, 2);
        
        TitledPane preferencesPane = new TitledPane("Préférences", grid);
        preferencesPane.setExpanded(false);
        
        return preferencesPane;
    }
    
    private TitledPane createItineraryPane(ComboBox<StatusUser> statusComboBox) {
        VBox mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(10));
        
        // Type of course
        ComboBox<TypeCourse> typeCourseComboBox = new ComboBox<>(
            FXCollections.observableArrayList(TypeCourse.values())
        );
        typeCourseComboBox.setValue(TypeCourse.ALLER_SIMPLE);
        
        // Disponibilite type
        ComboBox<DisponibiliteType> dispoTypeComboBox = new ComboBox<>(
            FXCollections.observableArrayList(DisponibiliteType.values())
        );
        dispoTypeComboBox.setValue(DisponibiliteType.QUOTIDIEN);
        
        HBox typesBox = new HBox(10);
        typesBox.getChildren().addAll(
            new Label("Type de course:"), typeCourseComboBox,
            new Label("Type de disponibilité:"), dispoTypeComboBox
        );
        
        // Itinerary points container
        VBox pointsContainer = new VBox(5);
        pointsContainer.setPadding(new Insets(10, 0, 10, 0));
        
        // For passenger: simple depart/arrivee
        GridPane passengerGrid = new GridPane();
        passengerGrid.setHgap(10);
        passengerGrid.setVgap(10);
        
        ComboBox<Communes> departComboBox = new ComboBox<>(
            FXCollections.observableArrayList(Communes.values())
        );
        departComboBox.setValue(Communes.USTHB);
        
        ComboBox<Communes> arriveeComboBox = new ComboBox<>(
            FXCollections.observableArrayList(Communes.values())
        );
        arriveeComboBox.setValue(Communes.BAB_EZZOUAR);
        
        passengerGrid.add(new Label("Point de départ:"), 0, 0);
        passengerGrid.add(departComboBox, 1, 0);
        passengerGrid.add(new Label("Point d'arrivée:"), 0, 1);
        passengerGrid.add(arriveeComboBox, 1, 1);
        
        // For chauffeur: multiple points
        VBox chauffeurGrid = new VBox(10);
        
        Label pointsLabel = new Label("Arrêts de l'itinéraire (dans l'ordre):");
        ListView<Communes> pointsListView = new ListView<>(
            FXCollections.observableArrayList(Communes.USTHB, Communes.BAB_EZZOUAR)
        );
        pointsListView.setPrefHeight(120);
        
        HBox pointsControlBox = new HBox(10);
        ComboBox<Communes> newPointComboBox = new ComboBox<>(
            FXCollections.observableArrayList(Communes.values())
        );
        Button addPointButton = new Button("Ajouter");
        Button removePointButton = new Button("Supprimer");
        Button moveUpButton = new Button("↑");
        Button moveDownButton = new Button("↓");
        
        pointsControlBox.getChildren().addAll(
            newPointComboBox, addPointButton, removePointButton, moveUpButton, moveDownButton
        );
        
        addPointButton.setOnAction(e -> {
            if (newPointComboBox.getValue() != null) {
                pointsListView.getItems().add(newPointComboBox.getValue());
            }
        });
        
        removePointButton.setOnAction(e -> {
            int selectedIdx = pointsListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx > 0) { // Prevent removing the first point (USTHB)
                pointsListView.getItems().remove(selectedIdx);
            } else {
                showAlert("Action non autorisée", "Le point de départ (USTHB) ne peut pas être supprimé.");
            }
        });
        
        moveUpButton.setOnAction(e -> {
            int selectedIdx = pointsListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx > 0) {
                Communes selected = pointsListView.getItems().get(selectedIdx);
                pointsListView.getItems().remove(selectedIdx);
                pointsListView.getItems().add(selectedIdx - 1, selected);
                pointsListView.getSelectionModel().select(selectedIdx - 1);
            }
        });
        
        moveDownButton.setOnAction(e -> {
            int selectedIdx = pointsListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx >= 0 && selectedIdx < pointsListView.getItems().size() - 1) {
                Communes selected = pointsListView.getItems().get(selectedIdx);
                pointsListView.getItems().remove(selectedIdx);
                pointsListView.getItems().add(selectedIdx + 1, selected);
                pointsListView.getSelectionModel().select(selectedIdx + 1);
            }
        });
        
        chauffeurGrid.getChildren().addAll(pointsLabel, pointsListView, pointsControlBox);
        
        // Number of places (only visible for CHAUFFEUR)
        HBox placesBox = new HBox(10);
        Label placesLabel = new Label("Nombre de places:");
        Spinner<Integer> placesSpinner = new Spinner<>(1, 8, 4);
        placesBox.getChildren().addAll(placesLabel, placesSpinner);
        
        // ========== TIME SELECTION SECTION ==========
        // Create a TitledPane for time selection
        TitledPane timeSelectionPane = new TitledPane();
        timeSelectionPane.setText("Horaires de disponibilité");
        
        // Container for both passenger and chauffeur time selection UIs
        StackPane timeSelectionContainer = new StackPane();
        
        // For passenger: single time selection
        VBox passengerTimeBox = new VBox(10);
        passengerTimeBox.setPadding(new Insets(10));
        
        ComboBox<Integer> hourComboBox = new ComboBox<>();
        for (int i = 0; i <= 23; i++) {
            hourComboBox.getItems().add(i);
        }
        hourComboBox.setValue(8); // Default to 8AM
        
        HBox hourSelectionBox = new HBox(10);
        hourSelectionBox.getChildren().addAll(new Label("Heure de disponibilité:"), hourComboBox, new Label("h"));
        
        passengerTimeBox.getChildren().add(hourSelectionBox);
        
        // For chauffeur: multiple time selection
        VBox chauffeurTimeBox = new VBox(10);
        chauffeurTimeBox.setPadding(new Insets(10));
        
        Label availableTimesLabel = new Label("Horaires de disponibilité:");
        
        // ListView to hold all selected times
        ListView<String> timeListView = new ListView<>();
        timeListView.setPrefHeight(100);
        timeListView.getItems().add("8h00"); // Default time
        
        // Time selection components
        HBox timeAddBox = new HBox(10);
        
        Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 8);
        hourSpinner.setEditable(true);
        hourSpinner.setPrefWidth(70);
        
        Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0);
        minuteSpinner.setEditable(true);
        minuteSpinner.setPrefWidth(70);
        
        Button addTimeButton = new Button("Ajouter");
        Button removeTimeButton = new Button("Supprimer");
        
        timeAddBox.getChildren().addAll(
            hourSpinner, new Label("h"), 
            minuteSpinner, new Label("min"), 
            addTimeButton, removeTimeButton
        );
        
        // Add time action
        addTimeButton.setOnAction(e -> {
            String timeStr = String.format("%dh%02d", hourSpinner.getValue(), minuteSpinner.getValue());
            if (!timeListView.getItems().contains(timeStr)) {
                timeListView.getItems().add(timeStr);
                
                // Sort times for readability
                timeListView.getItems().sort((t1, t2) -> {
                    int hour1 = Integer.parseInt(t1.split("h")[0]);
                    int min1 = Integer.parseInt(t1.split("h")[1]);
                    int hour2 = Integer.parseInt(t2.split("h")[0]);
                    int min2 = Integer.parseInt(t2.split("h")[1]);
                    
                    return hour1 * 60 + min1 - (hour2 * 60 + min2);
                });
            }
        });
        
        // Remove time action
        removeTimeButton.setOnAction(e -> {
            int selectedIdx = timeListView.getSelectionModel().getSelectedIndex();
            if (selectedIdx >= 0) {
                timeListView.getItems().remove(selectedIdx);
            }
        });
        
        chauffeurTimeBox.getChildren().addAll(availableTimesLabel, timeListView, timeAddBox);
        
        // Add both time selection interfaces to the container
        timeSelectionContainer.getChildren().addAll(passengerTimeBox, chauffeurTimeBox);
        timeSelectionPane.setContent(timeSelectionContainer);
        timeSelectionPane.setExpanded(false);
        
        // Initially hide chauffeur time box
        chauffeurTimeBox.setVisible(false);
        chauffeurTimeBox.setManaged(false);
        
        // Initially hide chauffeur grid and places box
        chauffeurGrid.setVisible(false);
        chauffeurGrid.setManaged(false);
        placesBox.setVisible(false);
        placesBox.setManaged(false);
        
        // Add listener to status combo box to toggle visibility
        statusComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == StatusUser.CHAUFFEUR) {
                // Show chauffeur-specific UI elements
                passengerGrid.setVisible(false);
                passengerGrid.setManaged(false);
                chauffeurGrid.setVisible(true);
                chauffeurGrid.setManaged(true);
                placesBox.setVisible(true);
                placesBox.setManaged(true);
                
                // Switch to chauffeur time selection
                passengerTimeBox.setVisible(false);
                passengerTimeBox.setManaged(false);
                chauffeurTimeBox.setVisible(true);
                chauffeurTimeBox.setManaged(true);
            } else {
                // Show passenger-specific UI elements
                passengerGrid.setVisible(true);
                passengerGrid.setManaged(true);
                chauffeurGrid.setVisible(false);
                chauffeurGrid.setManaged(false);
                placesBox.setVisible(false);
                placesBox.setManaged(false);
                
                // Switch to passenger time selection
                passengerTimeBox.setVisible(true);
                passengerTimeBox.setManaged(true);
                chauffeurTimeBox.setVisible(false);
                chauffeurTimeBox.setManaged(false);
            }
        });
        
        // Add all components to the container
        pointsContainer.getChildren().addAll(passengerGrid, chauffeurGrid);
        mainContainer.getChildren().addAll(typesBox, pointsContainer, placesBox, timeSelectionPane);
        
        TitledPane itineraryPane = new TitledPane("Itinéraire et Disponibilité", mainContainer);
        itineraryPane.setExpanded(false);
        
        return itineraryPane;
    }

    // Also update createBasicProfile to use the selected times from UI
    private Profile createBasicProfile(StatusUser status) {
        // This creates a simplified profile with default values
        // In a real application, you would get these values from the UI
        
        Preferences preferences = new Preferences(
            SexePreferences.SANS_PREFERENCE,
            MusiquePreferences.SANS_PREFERENCE,
            BagagesPreferences.SANS_PREFERENCE
        );
        
        List<Communes> points = new ArrayList<>();
        points.add(Communes.USTHB);
        points.add(Communes.BAB_EZZOUAR);
        
        Itineraire itineraire = new Itineraire(points);
        
        // Create list of times (these would come from the UI)
        List<LocalDateTime> horaires = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        
        if (status == StatusUser.CHAUFFEUR) {
            // For chauffeur, add multiple times (this is placeholder data)
            horaires.add(now.withHour(8).withMinute(0).withSecond(0));
            horaires.add(now.withHour(12).withMinute(30).withSecond(0));
            horaires.add(now.withHour(17).withMinute(0).withSecond(0));
        } else {
            // For passenger, add just one time
            horaires.add(now.withHour(8).withMinute(0).withSecond(0));
        }
        
        Disponibilite disponibilite = new Disponibilite(
            DisponibiliteType.QUOTIDIEN,
            horaires,
            itineraire
        );
        
        if (status == StatusUser.CHAUFFEUR) {
            return new Profile(status, TypeCourse.ALLER_SIMPLE, preferences, disponibilite, 4);
        } else {
            return new Profile(status, TypeCourse.ALLER_SIMPLE, preferences, disponibilite);
        }
    }
    
    private void updateUserList() {
        userListView.getItems().clear();
        for (User user : admin.getUtilisateurs()) {
            String userType = "Inconnu";
            if (user instanceof Etudiant) {
                userType = "Etudiant";
            } else if (user instanceof Enseignant) {
                userType = "Enseignant";
            } else if (user instanceof ATS) {
                userType = "ATS";
            }
            
            String statusType = user.getProfile().getStatut() == StatusUser.CHAUFFEUR ? 
                "Chauffeur" : "Passager";
            
            userListView.getItems().add(String.format("%s %s (%s) - %s - %s", 
                user.getNom(), 
                user.getPrenom(), 
                user.getMatricule(), 
                userType,
                statusType
            ));
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void updateUserReputation(Course course) {
        // Create reviews for both users
        Review passengerReview = new Review(
            5, // Default rating
            "Course completed successfully",
            course.getDriver(),
            course.getPassenger()
        );
        
        Review driverReview = new Review(
            5, // Default rating
            "Course completed successfully",
            course.getPassenger(),
            course.getDriver()
        );
        
        // Add reviews to update reputations
        course.getPassenger().addReview(passengerReview);
        course.getDriver().addReview(driverReview);
    }

    // Helper method to find a user from the selected list item
    private User findUserByListItem(String listItem) {
        String[] parts = listItem.split(" - ")[0].split(" ");
        if (parts.length < 3) return null;
        
        String matricule = parts[parts.length - 1].replaceAll("[()]", "");
        
        return admin.getUtilisateurs().stream()
            .filter(u -> u.getMatricule().equals(matricule))
            .findFirst()
            .orElse(null);
    }

    // Show user details dialog
    private void showUserDetailsDialog(User user) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Détails de l'utilisateur");
        dialog.setHeaderText(user.getNom() + " " + user.getPrenom());
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        int rowIndex = 0;
        
        // Basic user info with styled labels
        grid.add(new Label("Matricule:"), 0, rowIndex);
        Label matriculeLabel = new Label(user.getMatricule());
        matriculeLabel.getStyleClass().add("info-value");
        grid.add(matriculeLabel, 1, rowIndex++);
        
        grid.add(new Label("Statut:"), 0, rowIndex);
        Label statusLabel = new Label(user.getProfile().getStatut().toString());
        statusLabel.getStyleClass().add("status-badge");
        grid.add(statusLabel, 1, rowIndex++);
        
        grid.add(new Label("Réputation:"), 0, rowIndex);
        
        // Create a nice reputation display with stars
        HBox reputationBox = new HBox(5);
        Label reputationValue = new Label(String.format("%.1f", user.getReputation()));
        reputationValue.getStyleClass().add("reputation-value");
        
        // Add visual stars based on reputation
        reputationBox.getChildren().add(reputationValue);
        for (int i = 0; i < Math.round(user.getReputation()); i++) {
            Label star = new Label("★");
            star.getStyleClass().add("star-filled");
            reputationBox.getChildren().add(star);
        }
        
        grid.add(reputationBox, 1, rowIndex++);
        
        // User type specific info
        if (user instanceof Etudiant) {
            Etudiant etudiant = (Etudiant) user;
            grid.add(new Label("Type:"), 0, rowIndex);
            Label typeLabel = new Label("Étudiant");
            typeLabel.getStyleClass().add("user-type-label");
            grid.add(typeLabel, 1, rowIndex++);
            
            grid.add(new Label("Faculté:"), 0, rowIndex);
            grid.add(new Label(etudiant.getFaculte().toString()), 1, rowIndex++);
            
            grid.add(new Label("Spécialité:"), 0, rowIndex);
            grid.add(new Label(etudiant.getSpecialite().toString()), 1, rowIndex++);
            
            grid.add(new Label("Année d'admission:"), 0, rowIndex);
            grid.add(new Label(String.valueOf(etudiant.getAnneeAdmission())), 1, rowIndex++);
        } else if (user instanceof Enseignant) {
            Enseignant enseignant = (Enseignant) user;
            grid.add(new Label("Type:"), 0, rowIndex);
            Label typeLabel = new Label("Enseignant");
            typeLabel.getStyleClass().add("user-type-label");
            grid.add(typeLabel, 1, rowIndex++);
            
            grid.add(new Label("Faculté:"), 0, rowIndex);
            grid.add(new Label(enseignant.getFaculte().toString()), 1, rowIndex++);
            
            grid.add(new Label("Année de recrutement:"), 0, rowIndex);
            grid.add(new Label(String.valueOf(enseignant.getAnneeRecrutement())), 1, rowIndex++);
        } else if (user instanceof ATS) {
            ATS ats = (ATS) user;
            grid.add(new Label("Type:"), 0, rowIndex);
            Label typeLabel = new Label("ATS");
            typeLabel.getStyleClass().add("user-type-label");
            grid.add(typeLabel, 1, rowIndex++);
              
            grid.add(new Label("Année de recrutement:"), 0, rowIndex);
            grid.add(new Label(String.valueOf(ats.getAnneeRecrutement())), 1, rowIndex++);
            grid.add(new Label("Service:"), 0, rowIndex);
            grid.add(new Label(ats.getServiceRattachement()), 1, rowIndex++);
        }

        
        // Preferences
        TitledPane prefPane = new TitledPane();
        prefPane.setText("Préférences");
        
        GridPane prefGrid = new GridPane();
        prefGrid.setHgap(10);
        prefGrid.setVgap(5);
        prefGrid.setPadding(new Insets(10));
        
        prefGrid.add(new Label("Sexe:"), 0, 0);
        prefGrid.add(new Label(user.getProfile().getPreferences().getSexePreferences().toString().replace("_", " ")), 1, 0);
        
        prefGrid.add(new Label("Musique:"), 0, 1);
        prefGrid.add(new Label(user.getProfile().getPreferences().getMusiquePreferences().toString().replace("_", " ")), 1, 1);
        
        prefGrid.add(new Label("Bagages:"), 0, 2);
        prefGrid.add(new Label(user.getProfile().getPreferences().getBagagesPreferences().toString().replace("_", " ")), 1, 2);
        
        prefPane.setContent(prefGrid);
        grid.add(prefPane, 0, rowIndex++, 2, 1);
        
        // Reviews
        grid.add(new Label("Avis reçus:"), 0, rowIndex++);
        if (user.getReceivedReviews().isEmpty()) {
            grid.add(new Label("Aucun avis reçu"), 0, rowIndex++, 2, 1);
        } else {
            VBox reviewsBox = new VBox(5);
            for (Review review : user.getReceivedReviews()) {
                // Use the toString method from the Review class
                reviewsBox.getChildren().add(new Label(review.toString()));
            }
            
            ScrollPane scrollPane = new ScrollPane(reviewsBox);
            scrollPane.setPrefHeight(100);
            scrollPane.setFitToWidth(true);
            grid.add(scrollPane, 0, rowIndex++, 2, 1);
        }
        
        // Action buttons
        HBox actionButtons = new HBox(10);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);
        
        Button changeStatusButton = createIconButton("Changer statut", "🔄", "Changer le statut de cet utilisateur");
        changeStatusButton.setOnAction(e -> {
            dialog.close();
            showChangeStatusDialog(user);
        });
        
        actionButtons.getChildren().add(changeStatusButton);
        grid.add(actionButtons, 0, rowIndex++, 2, 1);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    // Change status dialog with itinerary and availability options
    private void showChangeStatusDialog(User user) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Changer le statut");
        dialog.setHeaderText("Modifier le profil de " + user.getNom() + " " + user.getPrenom());
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        // Status selection
        grid.add(new Label("Nouveau statut:"), 0, 0);
        ComboBox<StatusUser> statusComboBox = new ComboBox<>(
            FXCollections.observableArrayList(StatusUser.values())
        );
        statusComboBox.setValue(user.getProfile().getStatut());
        grid.add(statusComboBox, 1, 0);
        
        // Number of seats (visible only when CHAUFFEUR is selected)
        Label placesLabel = new Label("Nombre de places:");
        Spinner<Integer> placesSpinner = new Spinner<>(1, 10, 
            user.getProfile().getStatut() == StatusUser.CHAUFFEUR ? user.getProfile().getnbPlaces() : 4);
        placesLabel.setVisible(statusComboBox.getValue() == StatusUser.CHAUFFEUR);
        placesSpinner.setVisible(statusComboBox.getValue() == StatusUser.CHAUFFEUR);
        grid.add(placesLabel, 0, 1);
        grid.add(placesSpinner, 1, 1);
        
        // Update visibility when status changes
        statusComboBox.setOnAction(e -> {
            boolean isDriver = statusComboBox.getValue() == StatusUser.CHAUFFEUR;
            placesLabel.setVisible(isDriver);
            placesSpinner.setVisible(isDriver);
        });
        
        // Itinerary
        grid.add(new Label("Itinéraire:"), 0, 2);
        
        // Create a VBox to hold the itinerary points
        VBox pointsBox = new VBox(5);
        List<ComboBox<Communes>> pointComboBoxes = new ArrayList<>();
        
        // Add current itinerary points
        List<Communes> currentPoints = user.getProfile().getDisponibilite().getItineraire().getPoints();
        for (Communes point : currentPoints) {
            HBox pointRow = new HBox(5);
            ComboBox<Communes> pointComboBox = new ComboBox<>(
                FXCollections.observableArrayList(Communes.values())
            );
            pointComboBox.setValue(point);
            pointComboBoxes.add(pointComboBox);
            
            Button removeButton = new Button("-");
            removeButton.setOnAction(e -> {
                pointsBox.getChildren().remove(pointRow);
                pointComboBoxes.remove(pointComboBox);
            });
            
            pointRow.getChildren().addAll(pointComboBox, removeButton);
            pointsBox.getChildren().add(pointRow);
        }
        
        // Add button to add new points
        Button addPointButton = new Button("Ajouter un point");
        addPointButton.setOnAction(e -> {
            HBox pointRow = new HBox(5);
            ComboBox<Communes> pointComboBox = new ComboBox<>(
                FXCollections.observableArrayList(Communes.values())
            );
            pointComboBoxes.add(pointComboBox);
            
            Button removeButton = new Button("-");
            removeButton.setOnAction(event -> {
                pointsBox.getChildren().remove(pointRow);
                pointComboBoxes.remove(pointComboBox);
            });
            
            pointRow.getChildren().addAll(pointComboBox, removeButton);
            pointsBox.getChildren().add(pointRow);
        });
        
        ScrollPane pointsScrollPane = new ScrollPane(pointsBox);
        pointsScrollPane.setFitToWidth(true);
        pointsScrollPane.setPrefHeight(150);
        
        grid.add(pointsScrollPane, 1, 2);
        grid.add(addPointButton, 1, 3);
        
        // Availability times
        grid.add(new Label("Horaires:"), 0, 4);
        
        // Create a VBox to hold the time pickers
        VBox timesBox = new VBox(5);
        List<Spinner<Integer>> hourSpinners = new ArrayList<>();
        List<Spinner<Integer>> minuteSpinners = new ArrayList<>();
        
        // Add current times
        List<LocalDateTime> currentTimes = user.getProfile().getDisponibilite().getHoraires();
        for (LocalDateTime time : currentTimes) {
            HBox timeRow = new HBox(5);
            
            Spinner<Integer> hourSpinner = new Spinner<>(0, 23, time.getHour());
            Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, time.getMinute());
            hourSpinners.add(hourSpinner);
            minuteSpinners.add(minuteSpinner);
            
            Label timeLabel = new Label("h");
            
            Button removeButton = new Button("-");
            removeButton.setOnAction(e -> {
                timesBox.getChildren().remove(timeRow);
                hourSpinners.remove(hourSpinner);
                minuteSpinners.remove(minuteSpinner);
            });
            
            timeRow.getChildren().addAll(hourSpinner, timeLabel, minuteSpinner, removeButton);
            timesBox.getChildren().add(timeRow);
        }
        
        // Add button to add new times
        Button addTimeButton = new Button("Ajouter un horaire");
        addTimeButton.setOnAction(e -> {
            HBox timeRow = new HBox(5);
            
            Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 8);
            Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0);
            hourSpinners.add(hourSpinner);
            minuteSpinners.add(minuteSpinner);
            
            Label timeLabel = new Label("h");
            
            Button removeButton = new Button("-");
            removeButton.setOnAction(event -> {
                timesBox.getChildren().remove(timeRow);
                hourSpinners.remove(hourSpinner);
                minuteSpinners.remove(minuteSpinner);
            });
            
            timeRow.getChildren().addAll(hourSpinner, timeLabel, minuteSpinner, removeButton);
            timesBox.getChildren().add(timeRow);
        });
        
        ScrollPane timesScrollPane = new ScrollPane(timesBox);
        timesScrollPane.setFitToWidth(true);
        timesScrollPane.setPrefHeight(150);
        
        grid.add(timesScrollPane, 1, 4);
        grid.add(addTimeButton, 1, 5);
        
        // Add Save button
        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        
        // Show dialog and handle result
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == saveButtonType) {
            // Update status
            StatusUser newStatus = statusComboBox.getValue();
            user.getProfile().changerStatus(newStatus);
            
            // Update seats if driver
            if (newStatus == StatusUser.CHAUFFEUR) {
                // Need to create a new Profile for the user since number of places is set in constructor
                Profile oldProfile = user.getProfile();
                Profile newProfile = new Profile(
                    newStatus,
                    oldProfile.getTypeCourse(),
                    oldProfile.getPreferences(),
                    oldProfile.getDisponibilite(),
                    placesSpinner.getValue()
                );
                user.setProfile(newProfile);
            } else {
                // If changing to passenger, create a passenger profile
                Profile oldProfile = user.getProfile();
                Profile newProfile = new Profile(
                    newStatus,
                    oldProfile.getTypeCourse(),
                    oldProfile.getPreferences(),
                    oldProfile.getDisponibilite()
                );
                user.setProfile(newProfile);
            }
            
            // Update itinerary
            List<Communes> newPoints = pointComboBoxes.stream()
                .map(ComboBox::getValue)
                .filter(p -> p != null)
                .collect(Collectors.toList());
            
            if (!newPoints.isEmpty()) {
                user.getProfile().getDisponibilite().setItineraire(new Itineraire(newPoints));
            }
            
            // Update times
            LocalDateTime baseDate = LocalDateTime.of(2023, 1, 1, 0, 0);
            List<LocalDateTime> newTimes = new ArrayList<>();
            for (int i = 0; i < hourSpinners.size(); i++) {
                Integer hour = hourSpinners.get(i).getValue();
                Integer minute = minuteSpinners.get(i).getValue();
                newTimes.add(baseDate.withHour(hour).withMinute(minute));
            }
            
            if (!newTimes.isEmpty()) {
                user.getProfile().getDisponibilite().setHoraires(newTimes);
            }
            
            // Update user list
            updateUserList();
            showAlert("Succès", "Profil mis à jour avec succès.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}