﻿// 
// Copyright (c) 2010-2014 Yves Langisch. All rights reserved.
// http://cyberduck.ch/
// 
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
// 
// Bug fixes, suggestions and comments should be sent to:
// yves@cyberduck.ch
// 

using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Ch.Cyberduck.Ui.Core
{
    /// <summary>
    /// Group of commands which belongs to a view.
    /// </summary>
    public class Commands
    {
        private readonly List<Command> _commands = new List<Command>();

        /// <summary>
        /// Validate all commands
        /// </summary>
        public void Validate()
        {
            foreach (Command command in _commands)
            {
                command.Validate();
            }
        }

        public void Add(ToolStripItem[] toolStripItems, MenuItem[] menuItems, EventHandler clickDelegate,
            ValidateCommand validateDelegate)
        {
            _commands.Add(new Command(toolStripItems, menuItems, clickDelegate, validateDelegate));
        }

        /*
        public void Add(Control[] controls,
                        EventHandler clickDelegate,
                        ValidateCommand validateDelegate)
        {
            _commands.Add(new Command(null, controls, clickDelegate, validateDelegate));
        }
         */

        public void Add(Control control, EventHandler clickDelegate, ValidateCommand validateDelegate)
        {
            _commands.Add(new Command(null, null, new[] { control }, clickDelegate, validateDelegate));
        }

        public void Add(Control control, ValidateCommand validateDelegate)
        {
            _commands.Add(new Command(null, null, new[] { control }, delegate { }, validateDelegate));
        }

        public void Add(ToolStripItem[] items, Control[] controls, MenuItem[] menuItems, EventHandler clickDelegate,
            ValidateCommand validateDelegate)
        {
            _commands.Add(new Command(items, menuItems, controls, clickDelegate, validateDelegate));
        }

        /// <summary>
        /// Wraps a main menu item, a context menu item and toolstrip button into a command. A command can
        /// be validated (enable/disable) and executed.
        /// </summary>
        private class Command
        {
            private readonly EventHandler _clickCommandDelegate;
            private readonly Control[] _controls;
            private readonly MenuItem[] _menuItems;
            private readonly ToolStripItem[] _toolStripItems;
            private readonly ValidateCommand _validateCommandDelegate;
            private bool clicked = false;

            public Command(ToolStripItem[] toolStripItems, MenuItem[] menuItems, Control[] controls,
                EventHandler clickDelegate, ValidateCommand validateDelegate)
            {
                _toolStripItems = toolStripItems;
                _menuItems = menuItems;
                _controls = controls;
                _validateCommandDelegate = () => !clicked && validateDelegate();
                _clickCommandDelegate = (s, e) =>
                {
                    clicked = true;
                    Validate();
                    try
                    {
                        clickDelegate(s, e);
                    }
                    finally
                    {
                        clicked = false;
                    }
                };

                if (toolStripItems != null)
                    foreach (ToolStripItem item in toolStripItems)
                    {
                        item.Click += _clickCommandDelegate;
                    }

                if (controls != null)
                    foreach (Control control in controls)
                    {
                        control.Click += _clickCommandDelegate;
                    }
                if (menuItems != null)
                {
                    foreach (MenuItem item in menuItems)
                    {
                        item.Click += _clickCommandDelegate;
                    }
                }
            }

            public Command(ToolStripItem[] toolStripItems, MenuItem[] menuItems, EventHandler clickDelegate,
                ValidateCommand validateDelegate)
                : this(toolStripItems, menuItems, null, clickDelegate, validateDelegate)
            {
            }

            /// <summary>
            /// Validate this command.
            /// </summary>
            public void Validate()
            {
                bool enabled = _validateCommandDelegate();
                if (_toolStripItems != null)
                    foreach (ToolStripItem item in _toolStripItems)
                    {
                        item.Enabled = enabled;
                    }
                if (_controls != null)
                    foreach (Control control in _controls)
                    {
                        control.Enabled = enabled;
                    }
                if (_menuItems != null)
                {
                    foreach (var item in _menuItems)
                    {
                        item.Enabled = enabled;
                    }
                }
            }

            ~Command()
            {
                if (_toolStripItems != null)
                    foreach (ToolStripItem item in _toolStripItems)
                    {
                        item.Click -= _clickCommandDelegate;
                    }
                if (_controls != null)
                    foreach (Control control in _controls)
                    {
                        control.Click -= _clickCommandDelegate;
                    }
                //apparently we do not need to remove the click handler from any MenuItem. Seems
                //already removed at this point.
            }
        }
    }

    public delegate bool ValidateCommand();
}
