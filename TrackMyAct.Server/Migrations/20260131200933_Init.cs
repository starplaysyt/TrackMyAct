using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace TrackMyAct.Server.Migrations
{
    /// <inheritdoc />
    public partial class Init : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Interests",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Name = table.Column<string>(type: "TEXT", nullable: false),
                    Description = table.Column<string>(type: "TEXT", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Interests", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Places",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Name = table.Column<string>(type: "TEXT", nullable: false),
                    Description = table.Column<string>(type: "TEXT", nullable: false),
                    Address = table.Column<string>(type: "TEXT", nullable: false),
                    AvailableTime = table.Column<string>(type: "TEXT", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Places", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Username = table.Column<string>(type: "TEXT", nullable: false),
                    Email = table.Column<string>(type: "TEXT", nullable: false),
                    PasswordHash = table.Column<string>(type: "TEXT", nullable: false),
                    Name = table.Column<string>(type: "TEXT", nullable: false),
                    Phone = table.Column<string>(type: "TEXT", nullable: false),
                    BirthDate = table.Column<DateTime>(type: "TEXT", nullable: false),
                    UserType = table.Column<string>(type: "TEXT", maxLength: 13, nullable: false),
                    Organization = table.Column<string>(type: "TEXT", nullable: true),
                    BirthdayDate = table.Column<DateTime>(type: "TEXT", nullable: true),
                    IsVerifed = table.Column<bool>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Events",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Name = table.Column<string>(type: "TEXT", nullable: false),
                    Description = table.Column<string>(type: "TEXT", nullable: false),
                    StartDateTime = table.Column<DateTime>(type: "TEXT", nullable: false),
                    NeedAcception = table.Column<bool>(type: "INTEGER", nullable: false),
                    EventPlaceId = table.Column<int>(type: "INTEGER", nullable: false),
                    OrganizerId = table.Column<int>(type: "INTEGER", nullable: false),
                    InterestEntityId = table.Column<int>(type: "INTEGER", nullable: true),
                    OrganizerEntityId = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Events", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Events_Interests_InterestEntityId",
                        column: x => x.InterestEntityId,
                        principalTable: "Interests",
                        principalColumn: "Id");
                    table.ForeignKey(
                        name: "FK_Events_Places_EventPlaceId",
                        column: x => x.EventPlaceId,
                        principalTable: "Places",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Events_Users_OrganizerEntityId",
                        column: x => x.OrganizerEntityId,
                        principalTable: "Users",
                        principalColumn: "Id");
                    table.ForeignKey(
                        name: "FK_Events_Users_OrganizerId",
                        column: x => x.OrganizerId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "OrganizerSubscriptions",
                columns: table => new
                {
                    OrganizerSubscriptionsId = table.Column<int>(type: "INTEGER", nullable: false),
                    SubscribersId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_OrganizerSubscriptions", x => new { x.OrganizerSubscriptionsId, x.SubscribersId });
                    table.ForeignKey(
                        name: "FK_OrganizerSubscriptions_Users_OrganizerSubscriptionsId",
                        column: x => x.OrganizerSubscriptionsId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_OrganizerSubscriptions_Users_SubscribersId",
                        column: x => x.SubscribersId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "ParticipantInterests",
                columns: table => new
                {
                    InterestsId = table.Column<int>(type: "INTEGER", nullable: false),
                    ParticipantsId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ParticipantInterests", x => new { x.InterestsId, x.ParticipantsId });
                    table.ForeignKey(
                        name: "FK_ParticipantInterests_Interests_InterestsId",
                        column: x => x.InterestsId,
                        principalTable: "Interests",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_ParticipantInterests_Users_ParticipantsId",
                        column: x => x.ParticipantsId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Comments",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Text = table.Column<string>(type: "TEXT", nullable: false),
                    Type = table.Column<int>(type: "INTEGER", nullable: false),
                    Mark = table.Column<int>(type: "INTEGER", nullable: false),
                    AuthorId = table.Column<int>(type: "INTEGER", nullable: false),
                    EventId = table.Column<int>(type: "INTEGER", nullable: true),
                    OrganizerId = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Comments", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Comments_Events_EventId",
                        column: x => x.EventId,
                        principalTable: "Events",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Comments_Users_AuthorId",
                        column: x => x.AuthorId,
                        principalTable: "Users",
                        principalColumn: "Id");
                    table.ForeignKey(
                        name: "FK_Comments_Users_OrganizerId",
                        column: x => x.OrganizerId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "EventOrganizerRequests",
                columns: table => new
                {
                    OrganizerRequestsId = table.Column<int>(type: "INTEGER", nullable: false),
                    OrganizerRequestsId1 = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EventOrganizerRequests", x => new { x.OrganizerRequestsId, x.OrganizerRequestsId1 });
                    table.ForeignKey(
                        name: "FK_EventOrganizerRequests_Events_OrganizerRequestsId1",
                        column: x => x.OrganizerRequestsId1,
                        principalTable: "Events",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_EventOrganizerRequests_Users_OrganizerRequestsId",
                        column: x => x.OrganizerRequestsId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "EventParticipantRequests",
                columns: table => new
                {
                    ParticipantRequestsId = table.Column<int>(type: "INTEGER", nullable: false),
                    ParticipantRequestsId1 = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EventParticipantRequests", x => new { x.ParticipantRequestsId, x.ParticipantRequestsId1 });
                    table.ForeignKey(
                        name: "FK_EventParticipantRequests_Events_ParticipantRequestsId1",
                        column: x => x.ParticipantRequestsId1,
                        principalTable: "Events",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_EventParticipantRequests_Users_ParticipantRequestsId",
                        column: x => x.ParticipantRequestsId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "EventParticipants",
                columns: table => new
                {
                    AcceptedParticipantsId = table.Column<int>(type: "INTEGER", nullable: false),
                    ParticipationsId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EventParticipants", x => new { x.AcceptedParticipantsId, x.ParticipationsId });
                    table.ForeignKey(
                        name: "FK_EventParticipants_Events_ParticipationsId",
                        column: x => x.ParticipationsId,
                        principalTable: "Events",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_EventParticipants_Users_AcceptedParticipantsId",
                        column: x => x.AcceptedParticipantsId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "ParticipantArchiveEvents",
                columns: table => new
                {
                    ArchiveEventsId = table.Column<int>(type: "INTEGER", nullable: false),
                    ParticipantEntityId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ParticipantArchiveEvents", x => new { x.ArchiveEventsId, x.ParticipantEntityId });
                    table.ForeignKey(
                        name: "FK_ParticipantArchiveEvents_Events_ArchiveEventsId",
                        column: x => x.ArchiveEventsId,
                        principalTable: "Events",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_ParticipantArchiveEvents_Users_ParticipantEntityId",
                        column: x => x.ParticipantEntityId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Comments_AuthorId",
                table: "Comments",
                column: "AuthorId");

            migrationBuilder.CreateIndex(
                name: "IX_Comments_EventId",
                table: "Comments",
                column: "EventId");

            migrationBuilder.CreateIndex(
                name: "IX_Comments_OrganizerId",
                table: "Comments",
                column: "OrganizerId");

            migrationBuilder.CreateIndex(
                name: "IX_EventOrganizerRequests_OrganizerRequestsId1",
                table: "EventOrganizerRequests",
                column: "OrganizerRequestsId1");

            migrationBuilder.CreateIndex(
                name: "IX_EventParticipantRequests_ParticipantRequestsId1",
                table: "EventParticipantRequests",
                column: "ParticipantRequestsId1");

            migrationBuilder.CreateIndex(
                name: "IX_EventParticipants_ParticipationsId",
                table: "EventParticipants",
                column: "ParticipationsId");

            migrationBuilder.CreateIndex(
                name: "IX_Events_EventPlaceId",
                table: "Events",
                column: "EventPlaceId");

            migrationBuilder.CreateIndex(
                name: "IX_Events_InterestEntityId",
                table: "Events",
                column: "InterestEntityId");

            migrationBuilder.CreateIndex(
                name: "IX_Events_OrganizerEntityId",
                table: "Events",
                column: "OrganizerEntityId");

            migrationBuilder.CreateIndex(
                name: "IX_Events_OrganizerId",
                table: "Events",
                column: "OrganizerId");

            migrationBuilder.CreateIndex(
                name: "IX_OrganizerSubscriptions_SubscribersId",
                table: "OrganizerSubscriptions",
                column: "SubscribersId");

            migrationBuilder.CreateIndex(
                name: "IX_ParticipantArchiveEvents_ParticipantEntityId",
                table: "ParticipantArchiveEvents",
                column: "ParticipantEntityId");

            migrationBuilder.CreateIndex(
                name: "IX_ParticipantInterests_ParticipantsId",
                table: "ParticipantInterests",
                column: "ParticipantsId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Comments");

            migrationBuilder.DropTable(
                name: "EventOrganizerRequests");

            migrationBuilder.DropTable(
                name: "EventParticipantRequests");

            migrationBuilder.DropTable(
                name: "EventParticipants");

            migrationBuilder.DropTable(
                name: "OrganizerSubscriptions");

            migrationBuilder.DropTable(
                name: "ParticipantArchiveEvents");

            migrationBuilder.DropTable(
                name: "ParticipantInterests");

            migrationBuilder.DropTable(
                name: "Events");

            migrationBuilder.DropTable(
                name: "Interests");

            migrationBuilder.DropTable(
                name: "Places");

            migrationBuilder.DropTable(
                name: "Users");
        }
    }
}
