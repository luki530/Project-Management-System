SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE `administrators` (
  `id` int(11) NOT NULL,
  `user` varchar(12) COLLATE utf8_polish_ci NOT NULL,
  `project` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE `documents` (
  `title` text COLLATE utf8_polish_ci NOT NULL,
  `description` text COLLATE utf8_polish_ci NOT NULL,
  `topic` text COLLATE utf8_polish_ci NOT NULL,
  `creator` varchar(12) COLLATE utf8_polish_ci NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE `engineers` (
  `id` int(11) NOT NULL,
  `project` int(11) NOT NULL,
  `user` varchar(12) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE `hrs` (
  `id` int(11) NOT NULL,
  `project` int(11) NOT NULL,
  `user` varchar(12) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE `managers` (
  `id` int(11) NOT NULL,
  `project` int(11) NOT NULL,
  `user` varchar(12) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE `projects` (
  `id` int(11) NOT NULL,
  `title` text COLLATE utf8_polish_ci NOT NULL,
  `description` text COLLATE utf8_polish_ci NOT NULL,
  `creator` varchar(12) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE `users` (
  `login` varchar(12) COLLATE utf8_polish_ci NOT NULL,
  `first_name` text COLLATE utf8_polish_ci NOT NULL,
  `last_name` text COLLATE utf8_polish_ci NOT NULL,
  `e-mail` text COLLATE utf8_polish_ci NOT NULL,
  `password` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

ALTER TABLE `administrators`
  ADD PRIMARY KEY (`id`),
  ADD KEY `project` (`project`),
  ADD KEY `user` (`user`);

ALTER TABLE `documents`
  ADD PRIMARY KEY (`id`),
  ADD KEY `creator` (`creator`);

ALTER TABLE `engineers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `project` (`project`),
  ADD KEY `user` (`user`);

ALTER TABLE `hrs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `project` (`project`),
  ADD KEY `user` (`user`);

ALTER TABLE `managers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `project` (`project`),
  ADD KEY `user` (`user`);

ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `creator` (`creator`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`login`);

ALTER TABLE `administrators`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `documents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `engineers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `hrs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `managers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `administrators`
  ADD CONSTRAINT `administrators_ibfk_1` FOREIGN KEY (`project`) REFERENCES `projects` (`id`),
  ADD CONSTRAINT `administrators_ibfk_2` FOREIGN KEY (`user`) REFERENCES `users` (`login`);

ALTER TABLE `documents`
  ADD CONSTRAINT `documents_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `users` (`login`);

ALTER TABLE `engineers`
  ADD CONSTRAINT `engineers_ibfk_1` FOREIGN KEY (`project`) REFERENCES `projects` (`id`),
  ADD CONSTRAINT `engineers_ibfk_2` FOREIGN KEY (`user`) REFERENCES `users` (`login`);

ALTER TABLE `hrs`
  ADD CONSTRAINT `hrs_ibfk_1` FOREIGN KEY (`project`) REFERENCES `projects` (`id`),
  ADD CONSTRAINT `hrs_ibfk_2` FOREIGN KEY (`user`) REFERENCES `users` (`login`);

ALTER TABLE `managers`
  ADD CONSTRAINT `managers_ibfk_1` FOREIGN KEY (`project`) REFERENCES `projects` (`id`),
  ADD CONSTRAINT `managers_ibfk_2` FOREIGN KEY (`user`) REFERENCES `users` (`login`);

ALTER TABLE `projects`
  ADD CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `users` (`login`);
