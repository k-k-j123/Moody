
document.addEventListener("DOMContentLoaded", () => {
  const entriesSection = document.querySelector("main");
  const newEntryButton = document.querySelector(".neo-button.fixed");
  let userId = null;

  // Fetch the current user
  const fetchUser = async () => {
    try {
      const response = await fetch("/api/users/me");
      if (!response.ok) throw new Error("Failed to fetch user");
      const user = await response.json();
      userId = user.id;
      console.log("User ID:", userId);
      fetchEntries();
    } catch (error) {
      console.error("Error fetching user:", error);
    }
  };

  // Fetch and display entries for the current user
  const fetchEntries = async () => {
    try {
      const response = await fetch("/api/entries/me");
      if (!response.ok) throw new Error("Failed to fetch entries");
      const entries = await response.json();
      displayEntries(entries);
    } catch (error) {
      console.error("Error fetching entries:", error);
    }
  };

  const displayEntries = (entries) => {
    if (entries.length === 0) {
      entriesSection.innerHTML = `
        <h1 class="text-3xl font-bold">Create an entry now</h1>
        <p class="text-gray-600 dark:text-gray-300 mt-4">
          You don't have any journal entries yet. Click the "+ New Entry" button to start journaling!
        </p>
      `;
      return;
    }

    entriesSection.innerHTML = `<h1 class="text-3xl font-bold">Your Journal Entries</h1>`;

    entries.forEach(entry => {
      const sentimentClass = entry.compoundScore > 0 ? "text-green-600 dark:text-green-400" :
                             entry.compoundScore < 0 ? "text-red-600 dark:text-red-400" :
                             "text-gray-600 dark:text-gray-300";
      const sentimentText = entry.compoundScore > 0 ? "Positive" :
                            entry.compoundScore < 0 ? "Negative" : "Neutral";

      const entryCard = document.createElement("div");
      entryCard.className = "neo-card";
      entryCard.innerHTML = `
        <div class="flex justify-between items-center">
          <h2 class="text-xl font-bold">${entry.content}</h2>
          <button class="neo-button text-sm" data-id="${entry.id}">Delete</button>
        </div>
        <p class="text-sm mt-2 text-gray-600 dark:text-gray-300">${new Date(entry.createdAt).toLocaleString()}</p>
        <p class="mt-2">
          Sentiment: <span class="${sentimentClass} font-bold">${sentimentText}</span>
        </p>
      `;
      entriesSection.appendChild(entryCard);
    });

    // Attach delete handlers
    document.querySelectorAll(".neo-button.text-sm").forEach(button => {
      button.addEventListener("click", () => deleteEntry(button.dataset.id));
    });
  };

  const createEntry = async (content) => {
    try {
      const response = await fetch("/api/entries", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ content, user: { id: userId } })
      });
      if (!response.ok) throw new Error("Failed to create entry");
      fetchEntries();
    } catch (error) {
      console.error("Error creating entry:", error);
    }
  };

  const deleteEntry = async (id) => {
    try {
      const response = await fetch(`/api/entries/${id}`, {
        method: "DELETE"
      });
      if (!response.ok) throw new Error("Failed to delete entry");
      fetchEntries();
    } catch (error) {
      console.error("Error deleting entry:", error);
    }
  };

  const showCreateEntryPopup = () => {
    const popup = document.createElement("div");
    popup.className = "fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50";
    popup.innerHTML = `
      <div class="neo-card p-6 w-96">
        <h2 class="text-xl font-bold mb-4">Create New Entry</h2>
        <textarea id="entryContent" class="w-full p-2 border border-gray-300 rounded mb-4" rows="4" placeholder="Write your journal entry here..."></textarea>
        <div class="flex justify-end space-x-4">
          <button id="cancelButton" class="neo-button">Cancel</button>
          <button id="saveButton" class="neo-button">Save</button>
        </div>
      </div>
    `;
    document.body.appendChild(popup);

    document.getElementById("cancelButton").addEventListener("click", () => {
      document.body.removeChild(popup);
    });

    document.getElementById("saveButton").addEventListener("click", () => {
      const content = document.getElementById("entryContent").value.trim();
      if (content) {
        createEntry(content);
        document.body.removeChild(popup);
      } else {
        alert("Content cannot be empty!");
      }
    });
  };

  newEntryButton.addEventListener("click", showCreateEntryPopup);

  fetchUser(); // Initial call
});
